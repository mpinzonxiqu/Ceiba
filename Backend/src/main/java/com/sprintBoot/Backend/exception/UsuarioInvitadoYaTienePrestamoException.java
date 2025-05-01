package com.sprintBoot.Backend.exception;


public class UsuarioInvitadoYaTienePrestamoException extends RuntimeException {
    public UsuarioInvitadoYaTienePrestamoException(String identificacionUsuario) {
        super("El usuario con identificación " + identificacionUsuario +
                " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
    }
}
