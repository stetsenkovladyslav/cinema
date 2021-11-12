package com.example.admin.exception;

public class InvalidRatingValueException extends Throwable {

    private static final String MESSAGE = "Rating must be in this range [1, 10]";

    public InvalidRatingValueException() {
        super(MESSAGE);
    }
}

