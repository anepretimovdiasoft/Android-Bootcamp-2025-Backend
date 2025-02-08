package com.example.bootcamp.exceptions;

public class VolunteerAlreadyExistsException extends RuntimeException {
    public VolunteerAlreadyExistsException(String message) {
        super(message);
    }
}
