package ru.shortener.exception;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.shortener.dto.ErrorResponse;
import ru.shortener.exceptons.InvalidUrlException;
import ru.shortener.exceptons.ShortLinkNotFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<ErrorResponse> handleInvalidUrlException(InvalidUrlException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(400);
        return ResponseEntity.status(400).body(errorResponse);
    }
    @ExceptionHandler(ShortLinkNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleShortLinkNotFoundException(ShortLinkNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(404);
        return ResponseEntity.status(404).body(errorResponse);
    }
}
