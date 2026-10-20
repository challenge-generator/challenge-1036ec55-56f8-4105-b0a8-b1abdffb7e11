package com.banca.seguridad.tasks;

import com.banca.seguridad.utils.SecurityUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.RestQuestion;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.hamcrest.CoreMatchers.*;

public class VerificarVulnerabilidadSQL implements Task {

    private static final List<String> PAYLOADS_SQL_INJECTION = List.of(
        "' OR '1'='1",
        "' OR '1'='1' --",
        "'; DROP TABLE usuarios; --",
        "' UNION SELECT * FROM usuarios--",
        "1' AND '1'='1",
        "1' OR '1'='1' LIMIT 1--",
        "admin'--",
        "' OR 1=1--",
        "1; DELETE FROM transacciones WHERE '1'='1",
        "' WAITFOR DELAY '0:0:5'--"
    );

    private static final Pattern PATRON_SQLPeligROSO = Pattern.compile(
        "(?i)(.*(\\b(SELECT|INSERT|UPDATE|DELETE|DROP|UNION|ALTER|CREATE|TRUNCATE)\\b.*)|"
        + ".*(\\b(OR|AND)\\b\\s+['\\"][0-9]+['\\"]\\s*=\\s*['\\"][0-9]+['\\"])|"
        + ".*(--|\\#|\\/\\*|\\*\\/).*)"
    );

    private final String campo;
    private final String valorInyeccion;
    private final boolean esAutomatica;

    private VerificarVulnerabilidadSQL(Builder builder) {
        this.campo = builder.campo;
        this.valorInyeccion = builder.valorInyeccion;
        this.esAutomatica = builder.esAutomatica;
    }

    public static Builder enCampo(String campo, String valorInyeccion) {
        return new Builder().conCampo(campo).conValor(valorInyeccion);
    }

    public static VerificarVulnerabilidadSQL automatica(String endpoint) {
        return new Builder().conAutomatica(true).conEndpoint(endpoint).build();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<Map<String, Object>> resultados = new ArrayList<>();
        boolean vulnerabilidadDetectada = false;

        if (esAutomatica) {
            resultados = ejecutarPruebasAutomatizadas(actor);
        } else {
            resultados.add(ejecutarPruebaManual(actor, campo, valorInyeccion));
        }

        actor.remember("resultados_inyeccion_sql", resultados);

        for (Map<String, Object> resultado : resultados) {
            if (Boolean.TRUE.equals(resultado.get("vulnerable"))) {
                vulnerabilidadDetectada = true;
                String detalles = (String) resultado.get("detalles");
                SecurityUtils.registrarVulnerabilidad("SQL_INJECTION", campo, valorInyeccion, detalles);
            }
        }

        actor.should(seeThat("Detección de inyección SQL",
            actor1 -> vulnerabilidadDetectada,
            equalTo(false)
        ));
    }

    private List<Map<String, Object>> ejecutarPruebasAutomatizadas(Actor actor) {
        List<Map<String, Object>> resultados = new ArrayList<>();

        for (String payload : PAYLOADS_SQL_INJECTION) {
            Map<String, Object> resultado = ejecutarPruebaManual(actor, campo, payload);
            resultados.add(resultado);
        }

        return resultados;
    }

    private Map<String, Object> ejecutarPruebaManual(Actor actor, String campo, String payload) {
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("campo", campo);
        resultado.put("payload", payload);
        resultado.put("timestamp", System.currentTimeMillis());

        boolean esPayloadConocido = PAYLOADS_SQL_INJECTION.contains(payload);
        boolean coincidePatron = PATRON_SQLPeligROSO.matcher(payload).matches();

        if (!esPayloadConocido && !coincidePatron) {
            resultado.put("vulnerable", false);
            resultado.put("detalles", "Payload no reconocido como ataque SQL conocido");
            return resultado;
        }

        Map<String, Object> cuerpoSolicitud = new HashMap<>();
        cuerpoSolicitud.put(campo, payload);
        cuerpoSolicitud.put("tipoValidacion", "SQL_INJECTION_TEST");
        cuerpoSolicitud.put("origen", "AUTOMATED_SECURITY_TEST");

        actor.attemptsTo(
            Post.to("/api/v1/consultas/buscar")
                .with(request -> request
                    .contentType(ContentType.JSON)
                    .header("X-Request-ID", SecurityUtils.generarRequestId())
                    .header("X-Security-Test", "true")
                    .header("X-Test-Type", "SQL_INJECTION")
                    .body(cuerpoSolicitud)
                )
        );

        Response respuesta = RestQuestion.as(actor).lastResponse();
        int codigoEstado = respuesta.getStatusCode();

        boolean respuesta Sospechosa = false;
        String mensajeRespuesta = respuesta.getBody().asString();

        if (codigoEstado == 200) {
            String respuestaJson = respuesta.getBody().asString();
            if (respuestaJson.contains("SQLSyntaxErrorException")
                || respuestaJson.contains("syntax error")
                || respuestaJson.contains("ORA-")
                || respuestaJson.contains("MySQLSyntaxErrorException")) {
                respuestaSospechosa = true;
            }
        }

        if (codigoEstado == 500 || codigoEstado == 400 || respuestaSospechosa) {
            boolean vulnerable = verificarVulnerabilidad(codigoEstado, mensajeRespuesta, payload);
            resultado.put("vulnerable", vulnerable);
            resultado.put("codigoEstado", codigoEstado);
            resultado.put("respuesta Sospechosa", respuestaSospechosa);
            resultado.put("detalles", vulnerable ? 
                "Posible vulnerabilidad detectada: respuesta anomalous" : 
                "Solicitud bloqueada correctamente por el WAF");
        } else {
            resultado.put("vulnerable", false);
            resultado.put("codigoEstado", codigoEstado);
            resultado.put("detalles", "Solicitud procesada sin errores aparentes");
        }

        actor.remember("ultimo_test_sql", resultado);
        return resultado;
    }

    private boolean verificarVulnerabilidad(int codigoEstado, String respuesta, String payload) {
        if (codigoEstado == 500 && respuesta.contains("SQL")) {
            return true;
        }

        if (respuesta.toLowerCase().contains("syntax error near")
            || respuesta.contains("unterminated")
            || respuesta.contains("ORA-")) {
            return true;
        }

        if (payload.contains("UNION") && codigoEstado == 200) {
            return true;
        }

        return false;
    }

    public static class Builder {
        private String campo;
        private String valorInyeccion;
        private boolean esAutomatica = false;
        private String endpoint;

        public Builder conCampo(String campo) {
            this.campo = campo;
            return this;
        }

        public Builder conValor(String valorInyeccion) {
            this.valorInyeccion = valorInyeccion;
            return this;
        }

        public Builder conAutomatica(boolean automatica) {
            this.esAutomatica = automatica;
            return this;
        }

        public Builder conEndpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public VerificarVulnerabilidadSQL build() {
            return new VerificarVulnerabilidadSQL(this);
        }
    }
}