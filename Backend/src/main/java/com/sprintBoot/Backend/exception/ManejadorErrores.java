package com.sprintBoot.Backend.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ManejadorErrores {

    @ExceptionHandler(UsuarioInvitadoYaTienePrestamoException.class)
    public ResponseEntity<Map<String, String>> manejarInvitadoConPrestamo(UsuarioInvitadoYaTienePrestamoException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("mensaje", ex.getMessage()));
    }

    @ExceptionHandler(TipoUsuarioNoPermitidoException.class)
    public ResponseEntity<Map<String, String>> manejarTipoNoValido(TipoUsuarioNoPermitidoException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("mensaje", ex.getMessage()));
    }

    @ExceptionHandler(PrestamoNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> manejarPrestamoNoEncontrado(PrestamoNoEncontradoException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("mensaje", ex.getMessage()));
    }
}
