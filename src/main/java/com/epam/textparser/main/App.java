package com.epam.textparser.main;

import com.epam.textparser.parser.TextParser;
import com.epam.textparser.parser.TextParserBuilder;
import com.epam.textparser.parser.TextParserBuilder.RegexType;
import com.epam.textparser.textcomponents.ComponentContainer;
import com.epam.textparser.textloaders.TextLoader;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class App {
    static {
        PropertyConfigurator.configure(ClassLoader
                .getSystemClassLoader().getResourceAsStream("props/log4j.properties"));
    }

    private static Logger log = Logger.getLogger(App.class);

    public static void main(String[] args) {

        // Load text
        String textString = TextLoader
                .getInstance()
                .loadTextFromResources("text/text.txt");

        // Create text parser
        TextParserBuilder builder = new TextParserBuilder();
        ResourceBundle bundle = ResourceBundle.getBundle("props/patterns");
        Map<TextParserBuilder.RegexType, String> regexMap = new HashMap<>();
        regexMap.put(RegexType.PARAGRAPH, bundle.getString("paragraphRegExp"));
        regexMap.put(RegexType.SENTENCE, bundle.getString("sentenceRegExp"));
        regexMap.put(RegexType.TEXT_SOURCE_CODE, bundle.getString("textSourceCodeRegExp"));
        regexMap.put(RegexType.WORD, bundle.getString("wordRegExp"));
        regexMap.put(RegexType.SENTENCE_SOURCE_CODE, bundle.getString("sentenceSourceCode"));
        regexMap.put(RegexType.PUNCTUATION, bundle.getString("punctuation"));
        TextParser textParser = builder.build(regexMap);

        ComponentContainer text = textParser.parse(textString);
    }
}
