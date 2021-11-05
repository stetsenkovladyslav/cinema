package com.example.admin.controller;

import com.example.admin.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
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

}
