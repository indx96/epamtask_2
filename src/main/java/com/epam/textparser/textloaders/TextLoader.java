package com.epam.textparser.textloaders;

import java.util.Scanner;

public class TextLoader {
    private static TextLoader instance;

    public static TextLoader getInstance() {
        if (instance == null) {
            instance = new TextLoader();
        }
        return instance;
    }

    public String loadTextFromResources(String path) {
        Scanner in = new Scanner(
                ClassLoader.getSystemClassLoader().getResourceAsStream(path));
        return in.useDelimiter("\\Z").next();
    }

}
