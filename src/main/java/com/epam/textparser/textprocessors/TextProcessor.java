package com.epam.textparser.textprocessors;

import com.epam.textparser.exceptions.TextProcessingException;
import com.epam.textparser.textcomponents.ComponentContainer;
import com.epam.textparser.textcomponents.Lexeme;
import com.epam.textparser.textcomponents.TextComponent;

import java.util.Iterator;
import java.util.Optional;

/**
 * Created by ivan on 6/27/14.
 */
public class TextProcessor {
    private static TextProcessor instance;
    public static TextProcessor getInstance(){
        if (instance == null) {
            instance = new TextProcessor();
        }
        return instance;
    }

    private TextProcessor(){}

    public Optional<Lexeme> findUniqWordForFirstSentence(ComponentContainer text) {
        if (text.getType() != ComponentContainer.Type.TEXT){
            throw new TextProcessingException("text sholud be TEXT container type");
        }

        // Find first paragraph
        Optional<TextComponent> optionParagraph = text.getComponents()
                .stream()
                .filter((component) -> component instanceof ComponentContainer )
                .filter((component) -> ((ComponentContainer) component).getType() == ComponentContainer.Type.PARAGRAPH)
                .findFirst();
        if (!optionParagraph.isPresent()) {
            return Optional.empty();
        }

        // Find first sentence
        ComponentContainer paragraph  = (ComponentContainer)optionParagraph.get();
        Optional<TextComponent> optionSentence = paragraph.getComponents()
                .stream()
                .filter((component) -> component instanceof ComponentContainer)
                .filter((component) -> ((ComponentContainer) component).getType() == ComponentContainer.Type.SENTENCE)
                .findFirst();
        if (!optionSentence.isPresent()){
            return Optional.empty();
        }

        ComponentContainer sentence = (ComponentContainer) optionSentence.get();
        Iterator<TextComponent> iterator = sentence.getComponents()
                .stream()
                .filter((c) -> c instanceof Lexeme)
                .iterator();
        while (iterator.hasNext()){
            Lexeme lexeme =  (Lexeme) iterator.next();
            if (!isInRestText(text, sentence, lexeme)){
                return Optional.of(lexeme);
            }
        }

        return Optional.empty();
    }

    private boolean isInRestText(TextComponent component,
                                 ComponentContainer firstSentence,
                                 Lexeme word) {
        if (component instanceof ComponentContainer) {
            if (component == firstSentence){
                return false;
            }
            boolean result = false;
            for(TextComponent c : ((ComponentContainer)component).getComponents()) {
                result = result || isInRestText(c, firstSentence, word);
            }
            return result;
        } else {
            return ((Lexeme) component).equals(word);
        }
    }


}
