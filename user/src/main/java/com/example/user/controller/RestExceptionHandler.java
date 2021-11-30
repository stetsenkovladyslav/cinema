package com.example.user.controller;

import com.example.root.dto.ErrorMessage;
import com.example.root.exception.*;
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

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handleEntityAlreadyExistsException(UserAlreadyExistException e) {
        return new ErrorMessage()
                .setMessage(e.getMessage())
                .setStatus(HttpStatus.CONFLICT.value())
                .setTimestamp(Instant.now());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleBadRequestException(BadRequestException e) {
        return new ErrorMessage()
                .setMessage(e.getMessage())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setTimestamp(Instant.now());
    }

    @ExceptionHandler(FileFormatException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handleEntityAlreadyExistsException(FileFormatException e) {
        return new ErrorMessage()
                .setMessage(e.getMessage())
                .setStatus(HttpStatus.CONFLICT.value())
                .setTimestamp(Instant.now());
    }

    @ExceptionHandler(InvalidRatingValueException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handleEntityAlreadyExistsException(InvalidRatingValueException e) {
        return new ErrorMessage()
                .setMessage(e.getMessage())
                .setStatus(HttpStatus.CONFLICT.value())
                .setTimestamp(Instant.now());
    }

    @ExceptionHandler(UserRoleAdminException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handleEntityAlreadyExistsException(UserRoleAdminException e) {
        return new ErrorMessage()
                .setMessage(e.getMessage())
                .setStatus(HttpStatus.CONFLICT.value())
                .setTimestamp(Instant.now());
    }



}
