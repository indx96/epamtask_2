package com.epam.textparser.exceptions;

/**
 * Created by ivan on 6/27/14.
 */
public class TextProcessingException extends Exception {
    public TextProcessingException() {
    }

    public TextProcessingException(String message) {
        super(message);
    }

    public TextProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextProcessingException(Throwable cause) {
        super(cause);
    }
}
