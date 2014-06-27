package com.epam.textparser.parser;

import com.epam.textparser.textcomponents.ComponentContainer;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ivan on 6/25/14.
 * Converts given text to TEXT type ComponentContainer
 */
public class ParagraphParser {

    ParagraphParser(String sentenceRegExp,
                    SentenceParser sentenceParser) {
        pattern = Pattern.compile(sentenceRegExp, Pattern.DOTALL);
        this.sentenceParser = sentenceParser;
    }

    private SentenceParser sentenceParser;
    private Pattern pattern;
    private static Logger log = Logger.getLogger(ParagraphParser.class);

    ComponentContainer parse(String textParagraph) {
        Matcher matcher = pattern.matcher(textParagraph);
        ComponentContainer paragraph = new ComponentContainer(ComponentContainer.Type.PARAGRAPH);
        while (matcher.find()) {
            String textSentence = matcher.group();
            log.debug("found: " + textSentence);
            ComponentContainer sentence = sentenceParser.parse(textSentence);
            paragraph.addComponent(sentence);
        }
        return  paragraph;
    }
}
