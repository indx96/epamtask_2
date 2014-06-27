package com.epam.textparser.parser;

import com.epam.textparser.textcomponents.ComponentContainer;
import com.epam.textparser.textcomponents.Lexeme;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ivan on 6/26/14.
 */
public class SentenceParser {

    SentenceParser(String wordRegExp,
                   String sourceCodeRegExp,
                   String punctuationRegExp) {
        this.punctuationRegExp = punctuationRegExp;
        this.wordRegExp = wordRegExp;
        this.sourceCodeRegExp = sourceCodeRegExp;
    }

    private String punctuationRegExp;
    private String wordRegExp;
    private String sourceCodeRegExp;
    private static Logger log = Logger.getLogger(SentenceParser.class);

    public ComponentContainer parse(String textSentence) {
        Pattern pattern = Pattern.compile(wordRegExp + "|"
                + punctuationRegExp + "|" + sourceCodeRegExp, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(textSentence);
        ComponentContainer sentence = new ComponentContainer(ComponentContainer.Type.SENTENCE);

        while (matcher.find()){
            String textLexeme = matcher.group();
            log.debug("found: " + textLexeme);
            Lexeme.Type type;
            if (Pattern.matches(punctuationRegExp, textLexeme)) {
                log.debug("this is punctuation");
                type = Lexeme.Type.PUNCTUATION;
            } else if (Pattern.matches(wordRegExp, textLexeme)) {
                log.debug("this is word");
                type = Lexeme.Type.WORD;
            } else {
                log.debug("this is source code");
                type = Lexeme.Type.SOURCE_CODE;
            }
            Lexeme lexeme = new Lexeme(textLexeme, type);
            sentence.addComponent(lexeme);
        }

        return sentence;
    }
}
