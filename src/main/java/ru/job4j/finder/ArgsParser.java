package ru.job4j.finder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Парсер аргументов командной строки
 */
public class ArgsParser {
    private static final List<String> REQUIRED_PARAMS = List.of("d", "n", "t", "o");
    private static final Set<String> VALID_SEARCH_TYPES = Set.of("mask", "name", "regex");

    public Args parse(String[] args) {
        Map<String, String> params = new HashMap<>();
        for (String arg : args) {
            if (arg.startsWith("-") && arg.contains("=")) {
                String[] parts = arg.substring(1).split("=", 2);
                if (parts.length == 2) {
                    params.put(parts[0].toLowerCase(), parts[1]);
                }
            }
        }
        validateParams(params);
        return new Args(params);
    }

    private void validateParams(Map<String, String> params) {
        for (String key : REQUIRED_PARAMS) {
            if (!params.containsKey(key)) {
                throw new IllegalArgumentException("Missing required parameter: -" + key);
            }
        }

        String searchType = params.get("t");
        if (!VALID_SEARCH_TYPES.contains(searchType)) {
            throw new IllegalArgumentException("Invalid search type: " + searchType);
        }
    }
}
