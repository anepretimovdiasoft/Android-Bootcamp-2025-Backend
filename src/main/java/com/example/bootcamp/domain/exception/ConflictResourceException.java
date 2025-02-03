package com.example.bootcamp.domain.exception;

public class ConflictResourceException extends RuntimeException {
    public ConflictResourceException(String message) {
        // Вызывается, когда данные в таблицах конфликтуют.
        super(message);
    }
}
