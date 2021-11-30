package com.example.root.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FileFormatException extends ApplicationException {

    public FileFormatException(String message) {
        super(message);
    }

}
