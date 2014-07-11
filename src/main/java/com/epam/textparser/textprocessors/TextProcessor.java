package com.epam.textparser.textprocessors;

import com.epam.textparser.exceptions.TextProcessingException;
import com.epam.textparser.textcomponents.ComponentContainer;
import com.epam.textparser.textcomponents.Lexeme;
import com.epam.textparser.textcomponents.TextComponent;
import com.epam.textparser.textcomponents.TextComponentType;

import java.util.Iterator;
import java.util.Optional;

/**
 * Created by ivan on 6/27/14.
 */
public class TextProcessor {
    private static TextProcessor instance;

    private TextProcessor() {
    }

    public static TextProcessor getInstance() {
        if (instance == null) {
            instance = new TextProcessor();
        }
        return instance;
    }

    public Optional<Lexeme> findUniqueWordForFirstSentence(ComponentContainer text)
            throws TextProcessingException {
        if (text.getType() != TextComponentType.TEXT) {
            throw new TextProcessingException(new IllegalAccessException("text should be TEXT container type"));
        }

        // Find first paragraph
        Optional<TextComponent> optionParagraph = text.getComponents()
                .stream()
                .filter((component) -> component instanceof ComponentContainer)
                .filter((component) -> ((ComponentContainer) component).getType() == TextComponentType.PARAGRAPH)
                .findFirst();
        if (!optionParagraph.isPresent()) {
            return Optional.empty();
        }

        // Find first sentence
        ComponentContainer paragraph = (ComponentContainer) optionParagraph.get();
        Optional<TextComponent> optionSentence = paragraph.getComponents()
                .stream()
                .filter((component) -> component instanceof ComponentContainer)
                .filter((component) -> ((ComponentContainer) component).getType() == TextComponentType.SENTENCE)
                .findFirst();
        if (!optionSentence.isPresent()) {
            return Optional.empty();
        }

        ComponentContainer sentence = (ComponentContainer) optionSentence.get();
        Iterator<TextComponent> iterator = sentence.getComponents()
                .stream()
                .filter((c) -> c instanceof Lexeme)
                .filter((c) -> ((Lexeme) c).getType() == TextComponentType.WORD)
                .iterator();
        while (iterator.hasNext()) {
            Lexeme lexeme = (Lexeme) iterator.next();
            if (!isInRestText(text, sentence, lexeme)) {
                return Optional.of(lexeme);
            }
        }

        return Optional.empty();
    }

    private boolean isInRestText(TextComponent component,
                                 ComponentContainer firstSentence,
                                 Lexeme word) {
        if (component instanceof ComponentContainer) {
            if (component == firstSentence) {
                return false;
            }
            for (TextComponent c : ((ComponentContainer) component).getComponents()) {
                if (isInRestText(c, firstSentence, word)) {
                    return true;
                }
            }
            return false;
        } else {
            return component.equals(word);
        }
    }


}
