package com.epam.textparser.parser;

import com.epam.textparser.textcomponents.ComponentContainer;
import com.epam.textparser.textcomponents.Lexeme;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ivan on 6/25/14.
 * Converts given text to TEXT type ComponentContainer
 */
public class TextParser {
    TextParser(String paragraphRegExp,
                         String sourceCodeRegExp,
                         ParagraphParser paragraphParser) {
        this.paragraphRegExp = paragraphRegExp;
        this.paragraphParser = paragraphParser;
        this.sourceCodeRegExp = sourceCodeRegExp;
    }

    private String paragraphRegExp;
    private String sourceCodeRegExp;
    private ParagraphParser paragraphParser;
    private static Logger log = Logger.getLogger(TextParser.class);

    public ComponentContainer parse(String stringText) {
        Pattern pattern = Pattern.compile(paragraphRegExp + "|" + sourceCodeRegExp, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(stringText);
        ComponentContainer text = new ComponentContainer(ComponentContainer.Type.TEXT);

        while (matcher.find()) {
            String paragraphOrSource = matcher.group();
            log.debug("found: " + paragraphOrSource);
            if (Pattern.matches(paragraphRegExp, paragraphOrSource)) {
                log.debug("this is paragraph");
                ComponentContainer paragraph =
                        paragraphParser.parse(paragraphOrSource);
                text.addComponent(paragraph);
            } else {
                log.debug("this is code in text");
                Lexeme sourceCode =
                        new Lexeme(paragraphOrSource, Lexeme.Type.SOURCE_CODE);
                text.addComponent(sourceCode);
            }
        }

        return text;
    }


}
