package com.banca.seguridad.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.banca.seguridad.exceptions.SecurityException;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

public class ValidarAcceso implements Question<Boolean> {

    private final String recurso;
    private final String tipoAcceso;
    private final By locator;

    private ValidarAcceso(String recurso, String tipoAcceso, By locator) {
        this.recurso = recurso;
        this.tipoAcceso = tipoAcceso;
        this.locator = locator;
    }

    public static ValidarAcceso alRecurso(String recurso) {
        return new ValidarAcceso(recurso, "lectura", null);
    }

    public static ValidarAcceso alRecurso(String recurso, String tipoAcceso) {
        return new ValidarAcceso(recurso, tipoAcceso, null);
    }

    public ValidarAcceso enLaPagina(By locator) {
        return new ValidarAcceso(this.recurso, this.tipoAcceso, locator);
    }

    public static ValidarAcceso conPermisos(String recurso, String tipoPermiso) {
        return new ValidarAcceso(recurso, tipoPermiso, null);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            if (locator != null) {
                actor.attemptsTo(
                    WaitUntil.the(locator, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
                );
                
                WebElement elemento = locator.findElement(null);
                String contenido = elemento.getText();
                
                actor.should(
                    seeThat("El recurso debe ser accesible",
                        () -> contenido,
                        not(isEmptyString())
                    )
                );
                
                return validarPermisosAcceso(contenido);
            }
            
            return validarPermisosAcceso(actor.recall("ultimoAcceso"));
            
        } catch (Exception e) {
            throw new SecurityException(
                "Error al validar acceso al recurso: " + recurso + ", tipo: " + tipoAcceso, e
            );
        }
    }

    private boolean validarPermisosAcceso(String contenido) {
        if (contenido == null || contenido.isEmpty()) {
            return false;
        }
        
        if ("administrador".equals(tipoAcceso)) {
            return contenido.contains("ACCESS_GRANTED") || 
                   contenido.contains("ROLE_ADMIN") ||
                   contenido.contains("全権");
        }
        
        if ("lectura".equals(tipoAcceso)) {
            return contenido.contains("READ") || 
                   contenido.contains("LECTURA") ||
                   contenido.contains("VIEW");
        }
        
        if ("escritura".equals(tipoAcceso) || "modificacion".equals(tipoAcceso)) {
            return contenido.contains("WRITE") || 
                   contenido.contains("ESCRITURA") ||
                   contenido.contains("MODIFY");
        }
        
        return contenido.contains("ACCESS_GRANTED");
    }

    public String getRecurso() {
        return recurso;
    }

    public String getTipoAcceso() {
        return tipoAcceso;
    }

    public By getLocator() {
        return locator;
    }
}