package com.banca.seguridad.exceptions;

public class SecurityException extends RuntimeException {

    private String codigoError;
    private String nivelSeveridad;

    public SecurityException(String message) {
        super(message);
    }

    public SecurityException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityException(String message, String codigoError) {
        super(message);
        this.codigoError = codigoError;
    }

    public SecurityException(String message, String codigoError, String nivelSeveridad) {
        super(message);
        this.codigoError = codigoError;
        this.nivelSeveridad = nivelSeveridad;
    }

    public SecurityException(String message, String codigoError, Throwable cause) {
        super(message, cause);
        this.codigoError = codigoError;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getNivelSeveridad() {
        return nivelSeveridad;
    }

    public void setNivelSeveridad(String nivelSeveridad) {
        this.nivelSeveridad = nivelSeveridad;
    }
}