package com.example.promdetal_backend.search;

import java.util.List;
import java.util.Set;

public class SearchIndexBuilder {

    public static String build(String title) {
        List<String> stems = TextNormalizeUtil.normalize(title);
        Set<String> expanded = SynonymDictionary.expand(stems);
        return String.join(" ", expanded);
    }
}
