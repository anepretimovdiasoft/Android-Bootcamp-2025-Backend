package com.example.bootcamp.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String msg) {
        super(msg);
    }
}
