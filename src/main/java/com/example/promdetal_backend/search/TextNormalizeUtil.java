package com.example.promdetal_backend.search;

import org.tartarus.snowball.ext.RussianStemmer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextNormalizeUtil {

    public static List<String> normalize(String text) {
        return Arrays.stream(text.toLowerCase()
                        .replaceAll("[^а-яa-z0-9 ]", " ")
                        .split("\\s+"))
                .filter(s -> s.length() > 2)
                .map(TextNormalizeUtil::stem)
                .collect(Collectors.toList());
    }

    private static String stem(String word) {
        RussianStemmer stemmer = new RussianStemmer();
        stemmer.setCurrent(word);
        stemmer.stem();
        return stemmer.getCurrent();
    }
}