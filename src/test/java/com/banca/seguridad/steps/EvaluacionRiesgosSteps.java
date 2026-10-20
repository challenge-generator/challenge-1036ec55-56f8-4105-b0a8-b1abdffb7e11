package com.banca.seguridad.steps;

import com.banca.seguridad.tasks.VerificarVulnerabilidadSQL;
import com.banca.seguridad.config.SecurityConfig;
import com.banca.seguridad.exceptions.SecurityException;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
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
 * Step definitions para los escenarios de evaluación de riesgos de seguridad.
 * Implementa el patrón Screenplay para separar las interacciones del actor de la lógica de verificación.
 * Cada paso mapea una línea Gherkin a una acción ejecutable en el sistema bajo prueba.
 */
public class EvaluacionRiesgosSteps {
    
    private static final Logger log = LoggerFactory.getLogger(EvaluacionRiesgosSteps.class);
    
    @Managed
    private WebDriver driver;
    
    private Actor evaluador = Actor.named("Evaluador de Riesgos");
    private SecurityConfig securityConfig;
    private Map<String, String> parametrosEvaluacion;
    private Map<String, Object> resultadosAnalisis;
    private List<String> vulnerabilidadesDetectadas;
    private String nivelRiesgoCalculado;
    
    /**
     * Configura el entorno de evaluación de riesgos con los parámetros iniciales.
     * Prepara el actor, el driver y la configuración de seguridad para las pruebas.
     */
    @Dado("que el evaluador de seguridad tiene acceso al sistema de evaluación de riesgos")
    public void queElEvaluadorTieneAccesoAlSistema() {
        evaluador.can(BrowseTheWeb.with(driver));
        securityConfig = new SecurityConfig();
        parametrosEvaluacion = new HashMap<>();
        resultadosAnalisis = new HashMap<>();
        log.info("Evaluador de seguridad configurado para iniciar evaluación de riesgos");
    }
    
    @Dado("que se han definido los siguientes criterios de evaluación de riesgos:")
    public void queSeHanDefinidoLosCriterios(List<String> criterios) {
        for (String criterio : criterios) {
            parametrosEvaluacion.put(criterio, obtenerValorPorDefecto(criterio));
        }
        log.info("Criterios de evaluación configurados: {}", criterios);
    }
    
    @Cuando("el evaluador ejecuta el análisis de vulnerabilidades del sistema")
    public void elEvaluadorEjecutaElAnalisis() {
        try {
            evaluador.attemptsTo(VerificarVulnerabilidadSQL.enElSistema());
            resultadosAnalisis = securityConfig.ejecutarAnalisisVulnerabilidades(parametrosEvaluacion);
            vulnerabilidadesDetectadas = (List<String>) resultadosAnalisis.get("vulnerabilidades");
            log.info("Análisis de vulnerabilidades completado. Detectadas: {} vulnerabilidades", 
                    vulnerabilidadesDetectadas != null ? vulnerabilidadesDetectadas.size() : 0);
        } catch (Exception e) {
            log.error("Error al ejecutar análisis de vulnerabilidades: {}", e.getMessage());
            throw new SecurityException("Fallo en el análisis de vulnerabilidades", e);
        }
    }
    
    @Cuando("el evaluador clasifica las vulnerabilidades según su severidad")
    public void elEvaluadorClasificaLasVulnerabilidades() {
        if (vulnerabilidadesDetectadas != null && !vulnerabilidadesDetectadas.isEmpty()) {
            nivelRiesgoCalculado = securityConfig.clasificarPorSeveridad(vulnerabilidadesDetectadas);
            resultadosAnalisis.put("nivelRiesgo", nivelRiesgoCalculado);
            log.info("Vulnerabilidades clasificadas. Nivel de riesgo: {}", nivelRiesgoCalculado);
        } else {
            nivelRiesgoCalculado = "BAJO";
            resultadosAnalisis.put("nivelRiesgo", nivelRiesgoCalculado);
        }
    }
    
    @Cuando("el evaluador calcula el impacto potencial de cada vulnerabilidad")
    public void elEvaluadorCalculaElImpacto() {
        Map<String, Double> impactos = new HashMap<>();
        if (vulnerabilidadesDetectadas != null) {
            for (String vuln : vulnerabilidadesDetectadas) {
                double impacto = securityConfig.calcularImpacto(vuln, parametrosEvaluacion);
                impactos.put(vuln, impacto);
            }
        }
        resultadosAnalisis.put("impactos", impactos);
        log.info("Impactos calculados para {} vulnerabilidades", impactos.size());
    }
    
    @Entonces("el sistema debe mostrar el reporte de evaluación de riesgos")
    public void elSistemaDebeMostrarElReporte() {
        assertThat(resultadosAnalisis, is(notNullValue()));
        assertThat(resultadosAnalisis.containsKey("vulnerabilidades"), is(true));
        log.info("Reporte de evaluación generado exitosamente");
    }
    
    @Entonces("el nivel de riesgo calculado debe ser {string}")
    public void elNivelDeRiesgoDebeSer(String nivelEsperado) {
        assertThat(nivelRiesgoCalculado, is(equalTo(nivelEsperado)));
        log.info("Validación de nivel de riesgo completada: {}", nivelRiesgoCalculado);
    }
    
    @Y("el evaluador debe identificar las vulnerabilidades críticas que requieren atención inmediata")
    public void elEvaluadorDebeIdentificarVulnerabilidadesCriticas() {
        List<String> criticas = securityConfig.filtrarVulnerabilidadesCriticas(vulnerabilidadesDetectadas);
        assertThat(criticas, is(notNullValue()));
        log.info("Vulnerabilidades críticas identificadas: {}", criticas.size());
    }
    
    @Y("las vulnerabilidades deben estar asociadas a los controles de seguridad existentes")
    public void lasVulnerabilidadesDebenEstarAsociadas() {
        Map<String, String> controles = securityConfig.obtenerControlesExistentes();
        assertThat(controles, is(notNullValue()));
        assertThat(controles.isEmpty(), is(false));
        log.info("Controles de seguridad asociados: {} controles", controles.size());
    }
    
    @Entonces("el evaluador debe generar recomendaciones de mitigación")
    public void elEvaluadorDebeGenerarRecomendaciones() {
        List<String> recomendaciones = securityConfig.generarRecomendaciones(
            vulnerabilidadesDetectadas, 
            parametrosEvaluacion
        );
        assertThat(recomendaciones, is(notNullValue()));
        assertThat(recomendaciones.isEmpty(), is(false));
        resultadosAnalisis.put("recomendaciones", recomendaciones);
        log.info("Recomendaciones de mitigación generadas: {} recomendaciones", recomendaciones.size());
    }
    
    @Y("el sistema debe registrar la trazabilidad del análisis de riesgos con vigencia de {int} años")
    public void elSistemaDebeRegistrarLaTrazabilidad(int anosVigencia) {
        boolean registrado = securityConfig.registrarTrazabilidad(
            "ANALISIS_RIESGOS",
            resultadosAnalisis,
            anosVigencia
        );
        assertThat(registrado, is(true));
        log.info("Trazabilidad registrada con vigencia de {} años", anosVigencia);
    }
    
    private String obtenerValorPorDefecto(String criterio) {
        switch (criterio.toLowerCase()) {
            case "confidencialidad":
                return "ALTO";
            case "integridad":
                return "ALTO";
            case "disponibilidad":
                return "MEDIO";
            case "trazabilidad":
                return "REQUERIDA";
            default:
                return "MEDIO";
        }
    }
}