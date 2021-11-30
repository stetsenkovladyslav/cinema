package com.example.root.exception;

public abstract class ApplicationException extends RuntimeException {
    protected ApplicationException() {
        super();
    }

    protected ApplicationException(Throwable cause) {
        super(cause);
    }

    protected ApplicationException(String message) {
        super(message);
    }
}
