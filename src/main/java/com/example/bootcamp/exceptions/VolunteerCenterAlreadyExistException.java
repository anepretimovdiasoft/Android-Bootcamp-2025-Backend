package com.example.bootcamp.exceptions;

public class VolunteerCenterAlreadyExistException extends RuntimeException {
    public VolunteerCenterAlreadyExistException(String message) {
        super(message);
    }
}
