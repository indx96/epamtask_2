package com.epam.textparser.parser;

import com.epam.textparser.exceptions.TextParserBuildingException;

import java.util.Map;

/**
 * Created by ivan on 6/25/14.
 */
public class TextParserBuilder {
    public TextParser build(Map<RegexType, String> map)
            throws TextParserBuildingException {
        for (RegexType type : RegexType.values()) {
            if (map.get(type) == null) {
                throw new TextParserBuildingException(
                        new IllegalArgumentException(type + " regexp can't be null"));
            }
        }

        SentenceParser sentenceParser =
                new SentenceParser(map.get(RegexType.WORD),
                        map.get(RegexType.SENTENCE_SOURCE_CODE),
                        map.get(RegexType.PUNCTUATION));

        ParagraphParser paragraphParser =
                new ParagraphParser(map.get(RegexType.SENTENCE), sentenceParser);

        return new TextParser(map.get(RegexType.PARAGRAPH),
                map.get(RegexType.TEXT_SOURCE_CODE),
                paragraphParser);
    }

    public enum RegexType {
        PARAGRAPH,
        SENTENCE,
        TEXT_SOURCE_CODE,
        WORD,
        SENTENCE_SOURCE_CODE,
        PUNCTUATION
    }


}
