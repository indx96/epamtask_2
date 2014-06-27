package com.epam.textparser.exceptions;

import com.epam.textparser.textcomponents.Lexeme;

/**
 * Created by ivan on 6/25/14.
 */
public class LexemeNullException extends RuntimeException {

    public LexemeNullException(){
        this("Lexeme can't be null");
    }

    public LexemeNullException(String message) {
        super(message);
    }
}
