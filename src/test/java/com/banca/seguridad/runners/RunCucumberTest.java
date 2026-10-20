package com.banca.seguridad.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Runner principal para ejecutar los escenarios de pruebas de seguridad con Cucumber y Serenity.
 * Este runner coordina la ejecución de todos los features de seguridad definidos en el proyecto.
 * Configura el formato de los informes y los paquetes donde se encuentran los step definitions.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features/seguridad",
    glue = {
        "com.banca.seguridad.steps",
        "com.banca.seguridad.tasks",
        "com.banca.seguridad.questions",
        "com.banca.seguridad.utils",
        "com.banca.seguridad.config",
        "com.banca.seguridad.exceptions"
    },
    plugin = {
        "pretty",
        "json:target/cucumber-reports/cucumber.json",
        "html:target/cucumber-reports/cucumber-html-reports.html",
        "junit:target/cucumber-reports/cucumber-junit-results.xml"
    },
    tags = "@seguridad",
    dryRun = false,
    monochrome = false
)
public class RunCucumberTest {
    
    /**
     * Clase de configuración para ejecutar pruebas de seguridad con Cucumber y Serenity.
     * Los escenarios definidos en Gherkin se mapean a los step definitions y se ejecutan
     * utilizando el patrón Screenplay para mantener separación de responsabilidades.
     * 
     * Los resultados se agregan en informes HTML y JSON para análisis posterior.
     */
    public RunCucumberTest() {
        // Constructor vacío requerido por JUnit para ejecutar el runner
    }
}