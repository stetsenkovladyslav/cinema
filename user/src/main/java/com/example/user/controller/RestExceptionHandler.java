package com.example.user.controller;

import com.example.root.dto.ErrorMessage;
import com.example.root.exception.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
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

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorMessage handleAuthenticationException(AuthenticationException e) {
        return new ErrorMessage()
                .setMessage(e.getMessage())
                .setStatus(HttpStatus.UNAUTHORIZED.value())
                .setTimestamp(Instant.now());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleEntityNotFoundException(EntityNotFoundException e) {
        return new ErrorMessage()
                .setMessage(e.getMessage())
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setTimestamp(Instant.now());
    }
}
