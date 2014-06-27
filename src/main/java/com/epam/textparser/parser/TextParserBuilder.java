package com.epam.textparser.parser;

import com.epam.textparser.exceptions.BuilderParserException;

import java.util.Map;

/**
 * Created by ivan on 6/25/14.
 */
public class TextParserBuilder {
    public enum RegexType {
        PARAGRAPH,
        SENTENCE,
        TEXT_SOURCE_CODE,
        WORD,
        SENTENCE_SOURCE_CODE,
        PUNCTUATION
    }

    ;

    public TextParser build(Map<RegexType, String> map) {
        for (RegexType type : RegexType.values()) {
            if (map.get(type) == null) {
                throw new BuilderParserException(type + " regexp can't be null");
            }
        }

        SentenceParser sentenceParser =
                new SentenceParser(map.get(RegexType.WORD),
                        map.get(RegexType.SENTENCE_SOURCE_CODE),
                        map.get(RegexType.PUNCTUATION));

        ParagraphParser paragraphParser =
                new ParagraphParser(map.get(RegexType.SENTENCE), sentenceParser);

        TextParser textParser = new TextParser(map.get(RegexType.PARAGRAPH),
                map.get(RegexType.TEXT_SOURCE_CODE),
                paragraphParser);

        return textParser;
    }


}
