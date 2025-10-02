package com.doctor.file_processor.controller.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String fieldName = "Unknown";
        String fieldErrorMsg = "Unknown";
        FieldError fieldError = ex.getFieldError();

        if (fieldError != null) {
            fieldName = fieldError.getField();
            fieldErrorMsg = fieldError.getDefaultMessage();
        }
        return new ResponseEntity<>(String.format("Incorrect request body: %s - %s", fieldName, fieldErrorMsg), HttpStatus.BAD_REQUEST);
    }
}