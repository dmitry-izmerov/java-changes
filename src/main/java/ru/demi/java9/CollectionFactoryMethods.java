package ru.demi.java9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionFactoryMethods {
    private static final Logger log = LoggerFactory.getLogger(CollectionFactoryMethods.class);

    public static void main(String[] args) {
        var nums = List.of(1, 2, 3);
        nums.forEach(n -> log.info("num: {}", n));
        // cannot modify all these collections from factory methods
        // nums.add(4); throws UnsupportedOperationException

        var fruits = Set.of("apricot", "banana", "pear");
        fruits.forEach(f -> log.info("fruit: {}", f));

        var colorsPerFruit = Map.of("apricot", "orange", "banana", "yellow", "pear", "green");
        colorsPerFruit.forEach((f, c) -> log.info("fruit {} with color {}", f, c));

        var entries = Map.ofEntries(
            Map.entry("apricot", "orange"),
            Map.entry("banana", "yellow")
        );
        entries.forEach((f, c) -> log.info("fruit {} with color {}", f, c));
    }
}
