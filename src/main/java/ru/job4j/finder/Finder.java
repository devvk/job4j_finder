package ru.job4j.finder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Главный класс приложения для поиска файлов.
 */
public class Finder {

    public static void main(String[] args) {
        try {
            Args params = new ArgsParser().parse(args);

            FileSearcher searcher = new FileSearcher();
            List<Path> foundFiles = searcher.search(params);

            ResultWriter writer = new ResultWriter();
            writer.write(foundFiles, params.getOutputFile());

            System.out.printf("Search completed. Found %d files. Results saved to: %s%n",
                    foundFiles.size(), params.getOutputFile());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("IO Error: " + e.getMessage());
            System.exit(2);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            System.exit(3);
        }
    }
}
