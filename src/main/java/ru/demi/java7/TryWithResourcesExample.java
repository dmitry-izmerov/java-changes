package ru.demi.java7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TryWithResourcesExample {

    public static void main(String[] args) throws IOException {
        try (var reader = Files.newBufferedReader(Path.of("README.md"))) {
            reader.lines().forEach(System.out::println);
        }
    }
}
