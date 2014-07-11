package com.epam.textparser.parser;

import com.epam.textparser.textcomponents.ComponentContainer;
import com.epam.textparser.textcomponents.Lexeme;
import com.epam.textparser.textcomponents.TextComponentType;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ivan on 6/25/14.
 * Converts given text to TEXT type ComponentContainer
 */
public class TextParser {
    private static Logger log = Logger.getLogger(TextParser.class);
    private String paragraphRegExp;
    private String sourceCodeRegExp;
    private ParagraphParser paragraphParser;
    private Pattern pattern;

    TextParser(String paragraphRegExp,
               String sourceCodeRegExp,
               ParagraphParser paragraphParser) {
        this.paragraphRegExp = paragraphRegExp;
        this.paragraphParser = paragraphParser;
        this.sourceCodeRegExp = sourceCodeRegExp;
        pattern = Pattern.compile(paragraphRegExp + "|" + sourceCodeRegExp);
    }

    public ComponentContainer parse(String text) {
        Matcher matcher = pattern.matcher(text);
        ComponentContainer textContainer = new ComponentContainer(TextComponentType.TEXT);

        while (matcher.find()) {
            String paragraphOrSource = matcher.group();
            log.debug("found: " + paragraphOrSource);
            if (Pattern.matches(paragraphRegExp, paragraphOrSource)) {
                log.debug("this is paragraph");
                ComponentContainer paragraph =
                        paragraphParser.parse(paragraphOrSource);
                textContainer.addComponent(paragraph);
            } else if (Pattern.matches(sourceCodeRegExp, paragraphOrSource)) {
                log.debug("this is code in text");
                Lexeme sourceCode =
                        new Lexeme(paragraphOrSource, TextComponentType.SOURCE_CODE);
                textContainer.addComponent(sourceCode);
            }
        }

        return textContainer;
    }


}
