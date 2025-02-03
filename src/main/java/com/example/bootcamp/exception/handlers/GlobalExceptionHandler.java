package com.example.bootcamp.exception.handlers;

import com.example.bootcamp.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleCodeNotFoundException(UserNotFoundException e){
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    };
}
