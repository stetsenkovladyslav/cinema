package com.example.root.exception;

public class InvalidRatingValueException extends ApplicationException {

    private static final String MESSAGE = "Rating must be in this range [1, 10]";

    public InvalidRatingValueException() {
        super(MESSAGE);
    }
}

