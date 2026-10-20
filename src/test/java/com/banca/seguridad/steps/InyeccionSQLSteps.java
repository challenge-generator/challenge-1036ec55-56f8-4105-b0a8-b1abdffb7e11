package com.banca.seguridad.steps;

import com.banca.seguridad.config.SecurityConfig;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Enonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

/**
 * Step definitions para los escenarios de pruebas de inyección SQL.
 * Implementa verificaciones de seguridad para detectar y validar vulnerabilidades
 * de inyección SQL en la aplicación de banca en línea.
 * Utiliza el patrón Screenplay para separar las acciones del actor de las verificaciones.
 */
public class InyeccionSQLSteps {
    
    private static final Logger log = LoggerFactory.getLogger(InyeccionSQLSteps.class);
    
    @Managed
    private WebDriver driver;
    
    private Actor atacante = Actor.named("Potencial Atacante");
    private Actor analista = Actor.named("Analista de Seguridad");
    private SecurityConfig securityConfig;
    private Map<String, String> payloadsInyeccion;
    private Map<String, Boolean> resultadosPrueba;
    private List<String> camposVulnerables;
    private boolean vulnerabilidadDetectada;
    private String tipoInyeccion;
    
    /**
     * Configura el entorno de pruebas para inyección SQL.
     * Prepara los payloads de prueba y la configuración de seguridad.
     */
    @Dado("que el sistema de banca en línea tiene formularios de entrada de datos")
    public void queElSistemaTieneFormularios() {
        securityConfig = new SecurityConfig();
        payloadsInyeccion = new HashMap<>();
        resultadosPrueba = new HashMap<>();
        log.info("Sistema de banca configurado para pruebas de inyección SQL");
    }
    
    @Dado("que se han preparado los siguientes payloads de inyección SQL:")
    public void queSeHanPreparadoLosPayloads(List<Map<String, String>> payloads) {
        for (Map<String, String> payload : payloads) {
            String tipo = payload.get("tipo");
            String valor = payload.get("payload");
            payloadsInyeccion.put(tipo, valor);
        }
        log.info("Payloads de inyección SQL preparados: {} tipos", payloadsInyeccion.size());
    }
    
    @Cuando("el analista intenta inyectar código SQL en el campo {string}")
    public void elAnalistaIntentaInyectar(String campo) {
        try {
            String payload = payloadsInyeccion.get("basico");
            if (payload == null) {
                payload = "' OR '1'='1";
            }
            
            Map<String, String> datosPrueba = new HashMap<>();
            datosPrueba.put(campo, payload);
            
            boolean resultado = securityConfig.simularConsultaConInput(campo, payload);
            vulnerabilidadDetectada = resultado;
            resultadosPrueba.put(campo, resultado);
            
            log.info("Prueba de inyección SQL en campo '{}' completada. Resultado: vulnerable={}", 
                    campo, vulnerabilidadDetectada);
        } catch (Exception e) {
            log.error("Error durante prueba de inyección SQL: {}", e.getMessage());
            vulnerabilidadDetectada = false;
            resultadosPrueba.put(campo, false);
        }
    }
    
    @Cuando("el analista ejecuta una consulta UNION para extraer datos de otras tablas")
    public void elAnalistaEjecutaUnionQuery() {
        String payloadUnion = payloadsInyeccion.get("union");
        if (payloadUnion == null) {
            payloadUnion = "' UNION SELECT NULL, username, password FROM users--";
        }
        
        try {
            boolean resultadoUnion = securityConfig.simularConsultaConInput("busqueda", payloadUnion);
            vulnerabilidadDetectada = resultadoUnion;
            resultadosPrueba.put("union_query", resultadoUnion);
            tipoInyeccion = resultadoUnion ? "UNION" : "NINGUNA";
            log.info("Prueba UNION query completada. Tipo de inyección detectada: {}", tipoInyeccion);
        } catch (Exception e) {
            log.error("Error en prueba UNION: {}", e.getMessage());
            vulnerabilidadDetectada = false;
        }
    }
    
    @Cuando("el analista prueba técnicas de inyección basadas en tiempo de respuesta")
    public void elAnalistaPruebaInyeccionPorTiempo() {
        String payloadTiempo = payloadsInyeccion.get("tiempo");
        if (payloadTiempo == null) {
            payloadTiempo = "'; WAITFOR DELAY '0:0:5'--";
        }
        
        try {
            long tiempoInicio = System.currentTimeMillis();
            boolean resultadoTiempo = securityConfig.simularConsultaConInput("usuario", payloadTiempo);
            long tiempoFin = System.currentTimeMillis();
            long duracion = tiempoFin - tiempoInicio;
            
            vulnerabilidadDetectada = resultadoTiempo || duracion > 4000;
            resultadosPrueba.put("time_based", vulnerabilidadDetectada);
            log.info("Prueba de inyección basada en tiempo completada. Duración: {}ms", duracion);
        } catch (Exception e) {
            log.error("Error en prueba basada en tiempo: {}", e.getMessage());
            vulnerabilidadDetectada = false;
        }
    }
    
    @Entonces("el sistema debe detectar y bloquear el intento de inyección SQL")
    public void elSistemaDebeDetectarYBloquear() {
        boolean proteccionActiva = securityConfig.verificarProteccionInyeccionSQL();
        assertThat("La protección contra inyección SQL debe estar activa", proteccionActiva, is(true));
        log.info("Validación de protección contra inyección SQL completada");
    }
    
    @Entonces("el campo {string} debe ser vulnerable a inyección SQL")
    public void elCampoDebeSerVulnerable(String campo) {
        Boolean resultado = resultadosPrueba.get(campo);
        if (resultado == null) {
            resultado = false;
        }
        assertThat("El campo " + campo + " debe ser vulnerable", resultado, is(true));
        log.info("Validación de vulnerabilidad en campo '{}' completada", campo);
    }
    
    @Entonces("el sistema debe registrar el intento de inyección para auditoría")
    public void elSistemaDebeRegistrarElIntento() {
        boolean registrado = securityConfig.registrarIntentoInyeccion(resultadosPrueba);
        assertThat("El intento de inyección debe ser registrado", registrado, is(true));
        log.info("Intento de inyección SQL registrado para auditoría");
    }
    
    @Y("el sistema debe aplicar técnicas de sanitización a las entradas del usuario")
    public void elSistemaDebeAplicarSanitizacion() {
        boolean sanitizacionActiva = securityConfig.verificarSanitizacionEntradas();
        assertThat("La sanitización de entradas debe estar activa", sanitizacionActiva, is(true));
        log.info("Validación de sanitización de entradas completada");
    }
    
    @Y("el sistema debe utilizar consultas parametrizadas para prevenir inyección")
    public void elSistemaDebeUtilizarConsultasParametrizadas() {
        boolean usaParametrizacion = securityConfig.verificarConsultasParametrizadas();
        assertThat("Debe utilizar consultas parametrizadas", usaParametrizacion, is(true));
        log.info("Validación de uso de consultas parametrizadas completada");
    }
    
    @Y("el análisis debe identificar todos los puntos de entrada vulnerables")
    public void elAnalisisDebeIdentificarPuntosEntrada() {
        camposVulnerables = securityConfig.identificarPuntosEntradaVulnerables(resultadosPrueba);
        assertThat(camposVulnerables, is(notNullValue()));
        log.info("Puntos de entrada vulnerables identificados: {}", camposVulnerables.size());
    }
    
    @Entonces("el sistema debe mostrar las medidas de protección implementadas")
    public void elSistemaDebeMostrarMedidasProteccion() {
        Map<String, String> medidas = securityConfig.obtenerMedidasProteccion();
        assertThat(medidas, is(notNullValue()));
        assertThat(medidas.isEmpty(), is(false));
        log.info("Medidas de protección implementadas: {} medidas", medidas.size());
    }
    
    @Y("se debe generar un reporte de evaluación de inyección SQL")
    public void seDebeGenerarReporte() {
        Map<String, Object> reporte = new HashMap<>();
        reporte.put("resultados", resultadosPrueba);
        reporte.put("vulnerabilidadDetectada", vulnerabilidadDetectada);
        reporte.put("tipoInyeccion", tipoInyeccion);
        reporte.put("timestamp", System.currentTimeMillis());
        
        boolean generado = securityConfig.generarReporteInyeccion(reporte);
        assertThat("El reporte debe ser generado", generado, is(true));
        log.info("Reporte de evaluación de inyección SQL generado");
    }
}