package com.epam.textparser.exceptions;

/**
 * Created by ivan on 6/25/14.
 */
public class TextParserBuildingException extends Exception {
    public TextParserBuildingException() {
    }

    public TextParserBuildingException(String message) {
        super(message);
    }

    public TextParserBuildingException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextParserBuildingException(Throwable cause) {
        super(cause);
    }
}
