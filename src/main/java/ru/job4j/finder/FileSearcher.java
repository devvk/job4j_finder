package ru.job4j.finder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс для поиска файлов
 */
public class FileSearcher {
    public List<Path> search(Args args) throws IOException {
        Predicate<Path> condition = getSearchCondition(args.getPattern(), args.getSearchType());
        return searchFiles(args.getDirectory(), condition);
    }

    private Predicate<Path> getSearchCondition(String pattern, String searchType) {
        return switch (searchType) {
            case "regex" -> path -> path.getFileName().toString().matches(pattern);
            case "mask" -> {
                String regex = convertMaskToRegex(pattern);
                yield path -> path.getFileName().toString().matches(regex);
            }
            case "name" -> path -> path.getFileName().toString().equals(pattern);
            default -> throw new IllegalArgumentException("Unknown search type: " + searchType);
        };
    }

    private String convertMaskToRegex(String mask) {
        return mask.replace(".", "\\.")
                .replace('?', '.')
                .replace("*", ".*");
    }

    private List<Path> searchFiles(Path startDir, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(startDir, searcher);
        return searcher.getPaths();
    }
}
