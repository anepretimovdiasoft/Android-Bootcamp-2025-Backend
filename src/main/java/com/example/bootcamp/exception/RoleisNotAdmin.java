package com.example.bootcamp.exception;

public class RoleisNotAdmin extends RuntimeException {
    public RoleisNotAdmin(String message) {
        super(message);
    }
}
