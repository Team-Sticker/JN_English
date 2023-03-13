package com.example.jn_english.exception;

import com.example.jn_english.exception.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<ErrorResponse> CustomHandler(CustomException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().getMessage()),
                HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }
}
