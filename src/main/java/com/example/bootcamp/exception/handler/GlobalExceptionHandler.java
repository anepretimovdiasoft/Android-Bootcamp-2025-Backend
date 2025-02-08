package com.example.bootcamp.exception.handler;

import com.example.bootcamp.exception.PersonAlreadyExistsException;
import com.example.bootcamp.exception.PersonNotFoundException;
import com.example.bootcamp.exception.RoleisNotAdmin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<String> handlePersonNotFoundException(PersonNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersonAlreadyExistsException.class)
    public ResponseEntity<String> handlePersonAlreadyExistsException(PersonAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoleisNotAdmin.class)
    public ResponseEntity<String> hadnleRoleisNotAdmin(RoleisNotAdmin e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}