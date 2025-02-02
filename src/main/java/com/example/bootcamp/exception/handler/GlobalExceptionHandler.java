package com.example.bootcamp.exception.handler;

import com.example.bootcamp.exception.UserNotFoundException;
import com.example.bootcamp.exception.VolunteerCentreNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ResourceBundle;

import static liquibase.pro.license.keymgr.e.e;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(VolunteerCentreNotFoundException.class)
    public ResponseEntity<String> handleVolunteerCentreNotFoundException(VolunteerCentreNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
