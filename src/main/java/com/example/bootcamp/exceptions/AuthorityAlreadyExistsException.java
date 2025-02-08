package com.example.bootcamp.exceptions;

public class AuthorityAlreadyExistsException extends RuntimeException {
    public AuthorityAlreadyExistsException(String message) {
        super(message);
    }
}
