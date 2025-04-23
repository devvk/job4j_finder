package ru.job4j.finder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * Класс для хранения и валидации параметров поиска
 */
public class Args {
    private final Path directory;
    private final String pattern;
    private final String searchType;
    private final Path outputFile;

    public Args(Map<String, String> params) {
        this.directory = Path.of(params.get("d"));
        this.pattern = params.get("n");
        this.searchType = params.get("t");
        this.outputFile = Path.of(params.get("o"));
        validate();
    }

    private void validate() {
        if (!Files.exists(directory)) {
            throw new IllegalArgumentException("Directory does not exist: " + directory.toAbsolutePath());
        }
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException("Not a directory: " + directory.toAbsolutePath());
        }
    }

    public Path getDirectory() {
        return directory;
    }

    public String getPattern() {
        return pattern;
    }

    public String getSearchType() {
        return searchType;
    }

    public Path getOutputFile() {
        return outputFile;
    }
}