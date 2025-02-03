package com.example.bootcamp.domain.exception;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        // Доступ запрещен.
        super();
    }
}
