package com.epam.textparser.exceptions;

public class LexemeInvalidArgumentsException extends RuntimeException {
    public LexemeInvalidArgumentsException() {
    }

    public LexemeInvalidArgumentsException(String message) {
        super(message);
    }

    public LexemeInvalidArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public LexemeInvalidArgumentsException(Throwable cause) {
        super(cause);
    }
}

