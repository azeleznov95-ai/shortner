package ru.shortener.exception;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.shortener.dto.ErrorResponse;
import ru.shortener.exceptons.InvalidUrlException;
import ru.shortener.exceptons.ShortLinkNotFoundException;

import java.time.LocalDateTime;

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
<<<<<<< HEAD
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
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(400);
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(400).body(errorResponse);
=======
        errorResponse.setStatus(400);
        errorResponse.setBrokeAt(LocalDateTime.now());
        return ResponseEntity.status(400).body(errorResponse);
    }
    @ExceptionHandler(ShortLinkNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInvalidUrlException(ShortLinkNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatus(404);
        errorResponse.setBrokeAt(LocalDateTime.now());
        return ResponseEntity.status(404).body(errorResponse);
>>>>>>> 152fe1a (Stable backend: PostgreSQL + JPA + REST API working)
    }
}
