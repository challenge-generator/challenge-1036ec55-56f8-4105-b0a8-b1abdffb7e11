package com.banca.seguridad.tasks;

import com.banca.seguridad.config.SecurityConfig;
import com.banca.seguridad.exceptions.SecurityException;
import com.banca.seguridad.utils.SecurityUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.RestQuestion;
import net.thucydides.model.util.NameConverter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.hamcrest.CoreMatchers.*;

public class RealizarLogin implements Task {

    private final String usuario;
    private final String contrasena;
    private final boolean usar2FA;
    private final String codigo2FA;

    private RealizarLogin(Builder builder) {
        this.usuario = builder.usuario;
        this.contrasena = builder.contrasena;
        this.usar2FA = builder.usar2FA;
        this.codigo2FA = builder.codigo2FA;
    }

    public static Builder conCredenciales(String usuario, String contrasena) {
        return new Builder().conUsuario(usuario).conContrasena(contrasena);
    }

    public static RealizarLogin con2FA(String usuario, String codigo2FA) {
        return new Builder().conUsuario(usuario).conContrasena("temp_password").con2FA(codigo2FA);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (StringUtils.isBlank(usuario) || StringUtils.isBlank(contrasena)) {
            throw new SecurityException("Las credenciales no pueden estar vacías");
        }

        String contrasenaCifrada = SecurityUtils.cifrarContrasena(contrasena);

        Map<String, Object> cuerpoSolicitud = new HashMap<>();
        cuerpoSolicitud.put("username", usuario);
        cuerpoSolicitud.put("password", contrasenaCifrada);
        cuerpoSolicitud.put("aplicacion", "banca-online");
        cuerpoSolicitud.put("dispositivo", "test-device");
        cuerpoSolicitud.put("ipOrigen", "192.168.1.100");

        if (usar2FA && StringUtils.isNotBlank(codigo2FA)) {
            cuerpoSolicitud.put("codigo2FA", codigo2FA);
            cuerpoSolicitud.put("metodo2FA", "TOTP");
        }

        actor.attemptsTo(
            Post.to("/api/v1/autenticacion/login")
                .with(request -> request
                    .contentType(ContentType.JSON)
                    .header("X-Request-ID", SecurityUtils.generarRequestId())
                    .header("X-Client-Version", SecurityConfig.getClientVersion())
                    .body(cuerpoSolicitud)
                )
        );

        Response ultimaRespuesta = RestQuestion.as(actor).lastResponse();

        if (ultimaRespuesta.getStatusCode() == 200) {
            String tokenJWT = ultimaRespuesta.jsonPath().getString("token");
            String tokenCifrado = SecurityUtils.cifrarToken(tokenJWT);

            actor.remember("token_jwt", tokenJWT);
            actor.remember("token_cifrado", tokenCifrado);
            actor.remember("usuario", usuario);
            actor.remember("intentos_fallidos", 0);

            if (ultimaRespuesta.jsonPath().getString("refreshToken") != null) {
                actor.remember("refresh_token", ultimaRespuesta.jsonPath().getString("refreshToken"));
            }

            SecurityUtils.registrarEventoAutenticacion(usuario, true, "LOGIN_SUCCESS");
        } else if (ultimaRespuesta.getStatusCode() == 401) {
            Integer intentosPrevios = (Integer) actor.recall("intentos_fallidos");
            actor.remember("intentos_fallidos", intentosPrevios + 1);

            String mensajeError = ultimaRespuesta.jsonPath().getString("mensaje");
            SecurityUtils.registrarEventoAutenticacion(usuario, false, "LOGIN_FAILED: " + mensajeError);

            if (intentosPrevios + 1 >= SecurityConfig.getMaxIntentosLogin()) {
                throw new SecurityException("Cuenta bloqueada por demasiados intentos fallidos");
            }

            throw new SecurityException("Autenticación fallida: " + mensajeError);
        } else if (ultimaRespuesta.getStatusCode() == 423) {
            Long tiempoBloqueo = ultimaRespuesta.jsonPath().getLong("tiempoBloqueo");
            throw new SecurityException("Cuenta temporalmente bloqueada. Intentar en " + tiempoBloqueo + " segundos");
        } else {
            throw new SecurityException("Error de autenticación inesperado: " + ultimaRespuesta.getStatusCode());
        }
    }

    public static class Builder {
        private String usuario;
        private String contrasena;
        private boolean usar2FA = false;
        private String codigo2FA;

        public Builder conUsuario(String usuario) {
            this.usuario = usuario;
            return this;
        }

        public Builder conContrasena(String contrasena) {
            this.contrasena = contrasena;
            return this;
        }

        public Builder con2FA(String codigo2FA) {
            this.usar2FA = true;
            this.codigo2FA = codigo2FA;
            return this;
        }

        public RealizarLogin build() {
            return new RealizarLogin(this);
        }
    }
}