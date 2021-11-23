package com.example.admin.controller;

import com.example.root.dto.ErrorMessage;
import com.example.root.exception.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;


@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleGlobalException(Exception e) {
        e.printStackTrace();
        return new ErrorMessage()
                .setMessage("Unexpected error")
                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setTimestamp(Instant.now());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> userAlreadyExist(UserAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorMessage()
                        .setMessage(ex.getMessage())
                        .setStatus(HttpStatus.CONFLICT.value())
                        .setTimestamp(Instant.now()));
    }

}
