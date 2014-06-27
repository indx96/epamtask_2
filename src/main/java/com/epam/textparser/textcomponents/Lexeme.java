package com.epam.textparser.textcomponents;

import com.epam.textparser.exceptions.LexemeNullException;

/**
 * Created by ivan on 6/25/14.
 */
public class Lexeme implements TextComponent {
    private String lexeme;
    private Type type;
    public enum Type{PUNCTUATION, SOURCE_CODE, WORD};

    public Lexeme (String lexeme, Type type) {
        if (lexeme == null) {
            throw new LexemeNullException();
        }
        this.lexeme = lexeme;
        this.type = type;
    }

    public Type getType(){
        return type;
    }

    @Override
    public String toString() {
        return lexeme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lexeme)) {
            return false;
        }

        Lexeme lexeme1 = (Lexeme) o;

        if (!lexeme.equals(lexeme1.lexeme)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return lexeme.hashCode();
    }
}
