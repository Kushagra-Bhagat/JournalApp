package com.kushagra.journalapp.exception;

import com.kushagra.journalapp.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(org.springframework.dao.DuplicateKeyException.class)
    public ResponseEntity<?> handleDuplicateKey(DuplicateKeyException ex) {
        log.warn("Duplicate key error {}", ex.getMessage());

        ErrorResponse error = ErrorResponse.builder()
                .message("Username already exists")
                .status(HttpStatus.CONFLICT.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleOtherExceptions(Exception ex) {
        log.error("Unhandled exception caught in global handler", ex);

        ErrorResponse error = ErrorResponse.builder()
                .message("Unhandled error")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(error , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
