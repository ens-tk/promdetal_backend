package com.example.promdetal_backend.search;

import java.util.*;

public class SynonymDictionary {

    private static final Map<String, List<String>> SYNONYMS = Map.of(
            "мотор", List.of("двигател"),
            "двигател", List.of("мотор"),
            "насос", List.of("пневмокамерник", "монжус", "пневмотранспорт"),
            "пневмокамерник", List.of("насос", "монжус"),
            "монжус", List.of("насос", "пневмокамерник")
    );

    public static Set<String> expand(List<String> stems) {
        Set<String> result = new HashSet<>(stems);

        for (String stem : stems) {
            SYNONYMS.getOrDefault(stem, List.of())
                    .forEach(result::add);
        }
        return result;
    }
}
