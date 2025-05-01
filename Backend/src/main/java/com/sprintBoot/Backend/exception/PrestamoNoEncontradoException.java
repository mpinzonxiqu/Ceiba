package com.sprintBoot.Backend.exception;

public class PrestamoNoEncontradoException extends RuntimeException {
    public PrestamoNoEncontradoException(Long message) {
        super(message);
    }
}
