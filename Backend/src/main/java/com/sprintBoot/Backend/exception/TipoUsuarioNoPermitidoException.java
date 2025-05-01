package com.sprintBoot.Backend.exception;

public class TipoUsuarioNoPermitidoException extends RuntimeException {
    public TipoUsuarioNoPermitidoException() {
        super("Tipo de usuario no permitido en la biblioteca");
    }
}

