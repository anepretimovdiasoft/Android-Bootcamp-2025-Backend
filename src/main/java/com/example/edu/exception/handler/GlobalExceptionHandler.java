package com.example.edu.exception.handler;

import com.example.edu.exception.AlreadyExistsException;
import com.example.edu.exception.BadRequestException;
import com.example.edu.exception.ErrorResponse;
import com.example.edu.exception.NotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static ResponseEntity<ErrorResponse> buildErrorResponse(Exception e, HttpStatus status, WebRequest request, @Nullable String message) {
        ErrorResponse errorResponse = new ErrorResponse(
                ZonedDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message == null ? e.getMessage() : message,
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, WebRequest request) {
        Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
        logger.error("Unexpected error occurred", e);
        return buildErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR, request, "Unexpected error occurred");
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e, WebRequest request) {
        return buildErrorResponse(e, HttpStatus.BAD_REQUEST, request, null);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e, WebRequest request) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, request, null);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(AlreadyExistsException e, WebRequest request) {
        return buildErrorResponse(e, HttpStatus.CONFLICT, request, null);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(InvalidFormatException e, WebRequest request) {
        return buildErrorResponse(e, HttpStatus.BAD_REQUEST, request, "Bad request payload format: failed to parse");
    }
}
