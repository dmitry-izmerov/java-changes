package ru.demi.java8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FunctionsExample {
    private static final Logger log = LoggerFactory.getLogger(FunctionsExample.class);

    public static void main(String[] args) {
        // Consumer
        Consumer<Integer> consumer = i -> log.info("value: {}", i);
        IntStream.rangeClosed(1, 5).boxed().forEach(consumer);

        // Operators
        BinaryOperator<Integer> sum = (a, b) -> a + b;
        sum.apply(1, 2);

        // Function
        Function<String, Integer> parseInt = string -> Integer.parseInt(string);
        parseInt.apply("1");

        // Predicate
        Predicate<String> predicate = string -> string.length() > 4;
        List.of("you", "some", "bingo", "wealth", "germany").stream()
            .filter(predicate)
            .collect(Collectors.toList());

        // Supplier
        Supplier<String> supplier = () -> "result";
        supplier.get();
    }
}
