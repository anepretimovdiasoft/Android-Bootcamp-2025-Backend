package com.example.bootcamp.exceptions.handler;

import com.example.bootcamp.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VolunteerNotFoundException.class)
    public ResponseEntity<String> handleVolunteerNotFoundException(VolunteerNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VolunteerCenterNotFoundException.class)
    public ResponseEntity<String> handleVolunteerCenterNotFoundException(VolunteerCenterNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VolunteerCenterAlreadyExistException.class)
    public ResponseEntity<String> handleVolunteerCenterAlreadyExistException(VolunteerCenterAlreadyExistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(VolunteerAlreadyExistsException.class)
    public ResponseEntity<String> handleVolunteerCenterAlreadyExistException(VolunteerAlreadyExistsException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AuthorityNotFoundException.class)
    public ResponseEntity<String> handleVolunteerCenterAlreadyExistException(AuthorityNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
