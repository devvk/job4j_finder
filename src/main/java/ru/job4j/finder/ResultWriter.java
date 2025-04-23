package ru.job4j.finder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Класс для записи результатов поиска
 */
public class ResultWriter {
    private static final String OUTPUT_DIR = "data";

    public void write(List<Path> files, Path outputFileName) throws IOException {
        Path outputFile = Path.of(OUTPUT_DIR).resolve(outputFileName);
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile)) {
            for (Path file : files) {
                writer.write(file.toString());
                writer.newLine();
            }
        }
    }
}
