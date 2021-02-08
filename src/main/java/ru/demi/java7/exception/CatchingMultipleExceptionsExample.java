package ru.demi.java7.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CatchingMultipleExceptionsExample {
    private static final Logger log = LoggerFactory.getLogger(CatchingMultipleExceptionsExample.class);

    public static void main(String[] args) {
        try {
            Class.forName(args[0]);
            Files.readAllLines(Path.of(args[1]));
        } catch (ClassNotFoundException | IOException e) {
            log.error("Error happened", e);
        }
    }
}
