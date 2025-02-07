package com.example.edu.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private ZonedDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
