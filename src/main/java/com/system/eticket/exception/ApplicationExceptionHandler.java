package com.system.eticket.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleException(EntityNotFoundException e) {
        Map<String, String> errorResponse = Map.of(
                "message", e.getLocalizedMessage(),
                "status", HttpStatus.NOT_FOUND.toString()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleException(IllegalArgumentException e) {
        Map<String, String> errorResponse = Map.of(
                "message", e.getLocalizedMessage(),
                "status", HttpStatus.BAD_REQUEST.toString()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        Map<String, String> errorResponse = Map.of(
                "message", e.getLocalizedMessage(),
                "status", HttpStatus.INTERNAL_SERVER_ERROR.toString()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}