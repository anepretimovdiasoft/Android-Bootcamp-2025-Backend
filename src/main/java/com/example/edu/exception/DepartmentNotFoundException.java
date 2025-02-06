package com.example.edu.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(String msg) {
        super(msg);
    }
}
