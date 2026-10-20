package com.banca.seguridad.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence;
import io.restassured.response.Response;
import org.json.JSONObject;
import com.banca.seguridad.exceptions.SecurityException;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.List;

public class ValidarVulnerabilidad implements Question<Boolean> {

    private final String tipoVulnerabilidad;
    private final String endpoint;
    private final String payload;
    private final Pattern patronSQLInjection;
    private final Pattern patronXSS;
    private final Pattern patronPathTraversal;

    private ValidarVulnerabilidad(String tipoVulnerabilidad, String endpoint, String payload) {
        this.tipoVulnerabilidad = tipoVulnerabilidad;
        this.endpoint = endpoint;
        this.payload = payload;
        this.patronSQLInjection = Pattern.compile(
            "(?i)(union.*select|select.*from|insert.*into|delete.*from|drop.*table|'\s*or\s*'1'='1|--)",
            Pattern.CASE_INSENSITIVE
        );
        this.patronXSS = Pattern.compile(
            "(?i)(<script|javascript:|onerror=|onload=|alert\(|document\.cookie)",
            Pattern.CASE_INSENSITIVE
        );
        this.patronPathTraversal = Pattern.compile(
            "(\.\./|\.\.\\/|%2e%2e/|%2e%2e\\|/etc/passwd|/windows/system32)",
            Pattern.CASE_INSENSITIVE
        );
    }

    public static ValidarVulnerabilidad deTipo(String tipo) {
        return new ValidarVulnerabilidad(tipo, null, null);
    }

    public static ValidarVulnerabilidad sql(String endpoint, String payload) {
        return new ValidarVulnerabilidad("SQL_INJECTION", endpoint, payload);
    }

    public static ValidarVulnerabilidad xss(String endpoint, String payload) {
        return new ValidarVulnerabilidad("XSS", endpoint, payload);
    }

    public static ValidarVulnerabilidad pathTraversal(String endpoint, String payload) {
        return new ValidarVulnerabilidad("PATH_TRAVERSAL", endpoint, payload);
    }

    public ValidarVulnerabilidad enEndpoint(String endpoint) {
        return new ValidarVulnerabilidad(this.tipoVulnerabilidad, endpoint, this.payload);
    }

    public ValidarVulnerabilidad conPayload(String payload) {
        return new ValidarVulnerabilidad(this.tipoVulnerabilidad, this.endpoint, payload);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            Response respuesta = actor.recall("ultimaRespuesta");
            
            if (respuesta == null) {
                throw new SecurityException(
                    "No se encontró respuesta previa para validar vulnerabilidad: " + tipoVulnerabilidad
                );
            }

            String cuerpoRespuesta = respuesta.getBody().asString();
            int codigoEstado = respuesta.getStatusCode();

            return analizarVulnerabilidad(cuerpoRespuesta, codigoEstado);
            
        } catch (Exception e) {
            throw new SecurityException(
                "Error al validar vulnerabilidad tipo: " + tipoVulnerabilidad, e
            );
        }
    }

    private boolean analizarVulnerabilidad(String cuerpo, int codigoEstado) {
        switch (tipoVulnerabilidad) {
            case "SQL_INJECTION":
                return detectarSQLInjection(cuerpo, codigoEstado);
            case "XSS":
                return detectarXSS(cuerpo, codigoEstado);
            case "PATH_TRAVERSAL":
                return detectarPathTraversal(cuerpo, codigoEstado);
            case "AUTH_BYPASS":
                return detectarAuthBypass(cuerpo, codigoEstado);
            case "IDOR":
                return detectarIDOR(cuerpo, codigoEstado);
            default:
                return false;
        }
    }

    private boolean detectarSQLInjection(String cuerpo, int codigoEstado) {
        List<String> indicadoresVulnerabilidad = Arrays.asList(
            "SQL syntax", "mysql_fetch", "ORA-", "PostgreSQL",
            "unterminated", "quoted string not properly terminated",
            "SQL error", "Warning: mysql", "SQLSTATE"
        );
        
        boolean indicadoresDB = indicadoresVulnerabilidad.stream()
            .anyMatch(cuerpo::contains);
        
        boolean erroresSQL = patronSQLInjection.matcher(cuerpo).find();
        
        return indicadoresDB || erroresSQL || codigoEstado == 500;
    }

    private boolean detectarXSS(String cuerpo, int codigoEstado) {
        List<String> indicadoresXSS = Arrays.asList(
            "<script>", "</script>", "<img", "onerror=",
            "onload=", "javascript:", "alert(", "document.cookie"
        );
        
        boolean contieneScriptsPeligrosos = indicadoresXSS.stream()
            .anyMatch(cuerpo::contains);
        
        boolean patronesXSS = patronXSS.matcher(cuerpo).find();
        
        return contieneScriptsPeligrosos || patronesXSS;
    }

    private boolean detectarPathTraversal(String cuerpo, int codigoEstado) {
        List<String> archivosSensibles = Arrays.asList(
            "/etc/passwd", "/etc/shadow", "windows\\system32",
            ".bashrc", ".git/config", "web.xml"
        );
        
        boolean expuestaInformacion = archivosSensibles.stream()
            .anyMatch(cuerpo::contains);
        
        boolean patronAtacante = patronPathTraversal.matcher(cuerpo).find();
        
        return expuestaInformacion || patronAtacante || codigoEstado == 403;
    }

    private boolean detectarAuthBypass(String cuerpo, int codigoEstado) {
        boolean sesionValida = cuerpo.contains("session_id") || 
                               cuerpo.contains("token") ||
                               cuerpo.contains("authenticated");
        
        boolean accesoSinAuth = codigoEstado == 200 && !sesionValida;
        
        return accesoSinAuth;
    }

    private boolean detectarIDOR(String cuerpo, int codigoEstado) {
        boolean datosOtrosUsuarios = cuerpo.contains("account_number") ||
                                     cuerpo.contains("balance") ||
                                     cuerpo.contains("transaction_history");
        
        return datosOtrosUsuarios && codigoEstado == 200;
    }

    public String getTipoVulnerabilidad() {
        return tipoVulnerabilidad;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getPayload() {
        return payload;
    }
}