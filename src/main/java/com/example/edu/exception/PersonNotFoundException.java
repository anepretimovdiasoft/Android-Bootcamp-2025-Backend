package com.example.edu.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String msg) {
        super(msg);
    }
}
