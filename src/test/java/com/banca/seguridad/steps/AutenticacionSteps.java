package com.banca.seguridad.steps;

import com.banca.seguridad.questions.ValidarAcceso;
import com.banca.seguridad.tasks.RealizarLogin;
import com.banca.seguridad.tasks.VerificarVulnerabilidadSQL;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.util.NameConverter;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;

public class AutenticacionSteps {

    @Dado("que el usuario {string} existe en el sistema con rol {string}")
    public void queElUsuarioExisteEnElSistemaConRol(String usuario, String rol) {
        Actor actor = OnStage.theActorCalled(NameConverter.forClass(usuario));
        actor.can(CallAnApi.at("https://api.banca-segura.com"));
    }

    @Cuando("el usuario {string} inicia sesión con contraseña {string}")
    public void elUsuarioIniciaSesionConContrasena(String usuario, String contrasena) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(RealizarLogin.conCredenciales(usuario, contrasena));
    }

    @Entonces("el sistema debe mostrar el mensaje de bienvenida")
    public void elSistemaDebeMostrarElMensajeDeBienvenida() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.aMenuPrincipal()));
    }

    @Y("el token JWT debe ser generado correctamente")
    public void elTokenJWTDebeSerGeneradoCorrectamente() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.conTokenValido()));
    }

    @Entonces("el sistema debe rechazar el acceso con mensaje de error de autenticación")
    public void elSistemaDebeRechazarElAccesoConMensajeDeError() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.conErrorDeAutenticacion()));
    }

    @Cuando("el usuario intenta acceder al recurso {string} sin autorización")
    public void elUsuarioIntentaAccederAlRecursoSinAutorizacion(String recurso) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(ValidarAcceso.alRecurso(recurso));
    }

    @Entonces("el sistema debe retornar código de error {int}")
    public void elSistemaDebeRetornarCodigoDeError(int codigoError) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.conCodigoDeEstado(codigoError)));
    }

    @Cuando("se intenta inyectar código SQL en el campo {string} con valor {string}")
    public void seIntentaInyectarCodigoSQLEnElCampo(String campo, String valorInyeccion) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(VerificarVulnerabilidadSQL.enCampo(campo, valorInyeccion));
    }

    @Entonces("el sistema debe bloquear la solicitud y registrar el intento de inyección")
    public void elSistemaDebeBloquearLaSolicitudYRegistrarElIntento() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.conIntentoDeInyeccionBloqueado()));
    }

    @Y("no debe ejecutar la consulta maliciosa")
    public void noDebeEjecutarLaConsultaMaliciosa() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.sinEjecucionDeInyeccion()));
    }

    @Dado("que el usuario {string} tiene un token JWT válido")
    public void queElUsuarioTieneUnTokenJWTValido(String usuario) {
        Actor actor = OnStage.theActorCalled(NameConverter.forClass(usuario));
        actor.can(CallAnApi.at("https://api.banca-segura.com"));
    }

    @Cuando("el usuario {string} accede a la pantalla de transacciones")
    public void elUsuarioAccedeALaPantallaDeTransacciones(String usuario) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(ValidarAcceso.aTransacciones());
    }

    @Entonces("el sistema debe permitir el acceso solo si el token tiene el permiso {string}")
    public void elSistemaDebePermitirElAccesoSoloSiElTokenTieneElPermiso(String permiso) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.conPermiso(permiso)));
    }

    @Cuando("se intercepta una solicitud de autenticación con token manipulado")
    public void seInterceptaUnaSolicitudDeAutenticacionConTokenManipulado() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(ValidarAcceso.conTokenManipulado());
    }

    @Entonces("el sistema debe rechazar el token manipulado")
    public void elSistemaDebeRechazarElTokenManipulado() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.conTokenInvalido()));
    }

    @Y("registrar el intento de acceso no autorizado en el log de seguridad")
    public void registrarElIntentoDeAccesoNoAutorizado() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.registradoEnLog()));
    }

    @Cuando("el usuario {string} intenta autenticarse con contraseña incorrecta {int} veces consecutivas")
    public void elUsuarioIntentaAutenticarseConContrasenaIncorrectaVeces(String usuario, int intentos) {
        Actor actor = OnStage.theActorInTheSpotlight();
        for (int i = 0; i < intentos; i++) {
            actor.attemptsTo(RealizarLogin.conCredenciales(usuario, "contrasena_incorrecta_" + i));
        }
    }

    @Entonces("el sistema debe bloquear temporalmente la cuenta del usuario")
    public void elSistemaDebeBloquearTemporalmenteLaCuenta() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.conCuentaBloqueada()));
    }

    @Y("mostrar mensaje indicando que la cuenta ha sido bloqueada por seguridad")
    public void mostrarMensajeIndicandoQueLaCuentaHaSidoBloqueada() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.conMensajeDeCuentaBloqueada()));
    }

    @Cuando("el usuario {string} usa autenticación de dos factores con código {string}")
    public void elUsuarioUsaAutenticacionDeDosFactores(String usuario, String codigo) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(RealizarLogin.con2FA(usuario, codigo));
    }

    @Entonces("el acceso debe ser concedido solo si el código 2FA es válido")
    public void elAccesoDebeSerConcedidoSoloSiElCodigo2FAEsValido() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.con2FAValido()));
    }
}