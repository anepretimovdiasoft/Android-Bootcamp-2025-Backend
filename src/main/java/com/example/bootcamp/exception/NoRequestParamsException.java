package com.example.bootcamp.exception;

public class NoRequestParamsException extends RuntimeException {
    public NoRequestParamsException(String message) {
        super(message);
    }
}
