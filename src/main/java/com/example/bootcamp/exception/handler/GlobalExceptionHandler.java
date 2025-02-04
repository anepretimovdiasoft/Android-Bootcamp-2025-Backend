package com.example.bootcamp.exception.handler;

import com.example.bootcamp.exception.CentreNotFoundException;
import com.example.bootcamp.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.bootcamp.exception.UserAlreadyExistsException;



@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CentreNotFoundException.class)
    public ResponseEntity<String> handleDepartmentNotFoundException(CentreNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handlePersonNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handlePersonAlreadyExistsException(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}