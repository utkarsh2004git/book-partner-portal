package com.capgemini.book_partner_portal.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {

        return ResponseEntity.status(500)
                .body(e.getMessage());
    }

    // Catch specific "Not Found" exceptions from Spring Data REST
    @ExceptionHandler(org.springframework.data.rest.webmvc.ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(Exception e) {
        return ResponseEntity.status(404)
                .body("The requested resource was not found: " + e.getMessage());
    }
}
