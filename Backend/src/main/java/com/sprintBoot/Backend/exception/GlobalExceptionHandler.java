package com.sprintBoot.Backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioInvitadoYaTienePrestamoException.class)
    public ResponseEntity<Map<String, String>> handleInvitadoConPrestamo(Exception e) {
        return ResponseEntity.badRequest().body(Map.of("mensaje", e.getMessage()));
    }

    @ExceptionHandler(TipoUsuarioNoPermitidoException.class)
    public ResponseEntity<Map<String, String>> handleTipoNoPermitido(Exception e) {
        return ResponseEntity.badRequest().body(Map.of("mensaje", e.getMessage()));
    }

    @ExceptionHandler(PrestamoNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handlePrestamoNoEncontrado(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", e.getMessage()));
    }
}

