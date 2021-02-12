package ru.demi.java8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FunctionalInterfaceExample {
    private static final Logger log = LoggerFactory.getLogger(FunctionalInterfaceExample.class);

    public static void main(String[] args) {
        Functional<String> funInterface = (param) -> log.info("param: {}", param);
        var words = List.of("first", "second");
        callFor(words, funInterface);
    }

    @FunctionalInterface
    interface Functional<T> {
        void call(T arg);
    }

    static <T> void callFor(List<T> items, Functional<T> function) {
        for (var item: items) {
            function.call(item);
        }
    }
}
