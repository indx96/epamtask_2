package com.epam.textparser.textcomponents;

import com.epam.textparser.exceptions.LexemeNullException;

public class Lexeme implements TextComponent {
    private String lexeme;
    private Type type;

    public Lexeme(String lexeme, Type type) {
        if (lexeme == null) {
            throw new LexemeNullException();
        }
        this.lexeme = lexeme;
        this.type = type;
    }


    public Type getType() {
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

        return lexeme.equals(lexeme1.lexeme);

    }

    @Override
    public int hashCode() {
        return lexeme.hashCode();
    }

    public enum Type {PUNCTUATION, SOURCE_CODE, WORD}
}
