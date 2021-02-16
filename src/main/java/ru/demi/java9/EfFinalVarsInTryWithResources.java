package ru.demi.java9;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EfFinalVarsInTryWithResources {

    public static void main(String[] args) throws IOException {
        final BufferedReader configReader = Files.newBufferedReader(Path.of("./build.gradle"));
        try (configReader) {
            configReader.readLine();
        }

        // effectively final
        var readmeReader = Files.newBufferedReader(Path.of("./README.md"));
        try (readmeReader) {
            readmeReader.readLine();
        }
    }
}
