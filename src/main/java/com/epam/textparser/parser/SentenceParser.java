package com.epam.textparser.parser;

import com.epam.textparser.textcomponents.ComponentContainer;
import com.epam.textparser.textcomponents.Lexeme;
import com.epam.textparser.textcomponents.TextComponentType;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ivan on 6/26/14.
 */
class SentenceParser {

    private static Logger log = Logger.getLogger(SentenceParser.class);
    private String punctuationRegExp;
    private String wordRegExp;
    private String sourceCodeRegExp;
    private Pattern pattern;

    SentenceParser(String wordRegExp,
                   String sourceCodeRegExp,
                   String punctuationRegExp) {
        this.punctuationRegExp = punctuationRegExp;
        this.wordRegExp = wordRegExp;
        this.sourceCodeRegExp = sourceCodeRegExp;
        pattern = Pattern.compile(sourceCodeRegExp + "|" + wordRegExp + "|"
                + punctuationRegExp);
    }

    public ComponentContainer parse(String textSentence) {
        Matcher matcher = pattern.matcher(textSentence);
        ComponentContainer sentence = new ComponentContainer(TextComponentType.SENTENCE);

        while (matcher.find()) {
            String textLexeme = matcher.group();
            log.debug("found: " + textLexeme);
            TextComponentType type = TextComponentType.PUNCTUATION;
            if (Pattern.matches(punctuationRegExp, textLexeme)) {
                log.debug("this is punctuation");
            } else if (Pattern.matches(wordRegExp, textLexeme)) {
                log.debug("this is word");
                type = TextComponentType.WORD;
            } else if (Pattern.matches(sourceCodeRegExp, textLexeme)) {
                log.debug("this is source code");
                type = TextComponentType.SOURCE_CODE;
            }
            Lexeme lexeme = new Lexeme(textLexeme, type);
            sentence.addComponent(lexeme);
        }

        return sentence;
    }
}
