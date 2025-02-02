package com.example.bootcamp.exception;

public class InvalidDescriptionException extends RuntimeException {
    public InvalidDescriptionException(String message) {
        super(message);
    }
}
