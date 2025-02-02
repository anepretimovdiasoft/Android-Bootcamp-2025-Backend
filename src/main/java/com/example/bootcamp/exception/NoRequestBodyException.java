package com.example.bootcamp.exception;

public class NoRequestBodyException extends RuntimeException {
    public NoRequestBodyException(String message) {
        super(message);
    }
}
