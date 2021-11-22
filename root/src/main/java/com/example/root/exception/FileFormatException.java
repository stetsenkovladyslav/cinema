package com.example.root.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FileFormatException extends RuntimeException {

    public FileFormatException(String message) {
        super(message);
    }

}
