package com.epam.textparser.textcomponents;

import com.epam.textparser.exceptions.LexemeInvalidArgumentsException;

public class Lexeme implements TextComponent {
    private String lexeme;
    private TextComponentType type;

    public Lexeme(String lexeme, TextComponentType type) {
        if (lexeme == null || type == null) {
            throw new LexemeInvalidArgumentsException(
                    new IllegalArgumentException("Text of lexeme can't be null. Type too."));
        }
        this.lexeme = lexeme;
        this.type = type;
    }


    public TextComponentType getType() {
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

}
