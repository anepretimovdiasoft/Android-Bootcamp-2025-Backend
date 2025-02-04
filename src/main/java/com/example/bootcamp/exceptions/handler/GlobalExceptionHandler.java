package com.example.bootcamp.exceptions.handler;

import com.example.bootcamp.exceptions.VolunteerCenterExistException;
import com.example.bootcamp.exceptions.VolunteerCenterNotFoundException;
import com.example.bootcamp.exceptions.VolunteerNotFoundException;
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

    @ExceptionHandler(VolunteerCenterExistException.class)
    public ResponseEntity<String> handleVolunteerCenterExistException(VolunteerCenterExistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}
