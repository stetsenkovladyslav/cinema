package com.example.user.controller;

import com.example.root.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorMessage handleAuthenticationException(AuthenticationException e) {
        return new ErrorMessage()
                .setMessage(e.getMessage())
                .setStatus(HttpStatus.UNAUTHORIZED.value())
                .setTimestamp(Instant.now());
    }
}
