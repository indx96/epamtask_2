package com.epam.textparser.exceptions;

public class LexemeNullException extends RuntimeException {

    public LexemeNullException(){
        this("Lexeme params can't be null");
    }

    public LexemeNullException(String message) {
        super(message);
    }
}
