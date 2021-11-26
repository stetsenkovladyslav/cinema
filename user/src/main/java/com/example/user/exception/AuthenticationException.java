package com.example.user.exception;

import javax.ejb.ApplicationException;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}
