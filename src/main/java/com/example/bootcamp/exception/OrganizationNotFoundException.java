package com.example.bootcamp.exception;

public class OrganizationNotFoundException extends RuntimeException{
    public OrganizationNotFoundException(String msg) {
        super(msg);
    }
}
