package com.example.bootcamp.domain.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        // Вызывается, когда что-то не найдено в бд.
        super(message);
    }
}
