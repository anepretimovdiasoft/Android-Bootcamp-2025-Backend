package com.example.edu.exception;

public class PersonAlreadyExistsException extends RuntimeException {
    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}
