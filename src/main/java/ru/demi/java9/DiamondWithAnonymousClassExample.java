package ru.demi.java9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class DiamondWithAnonymousClassExample {
    private static final Logger log = LoggerFactory.getLogger(DiamondWithAnonymousClassExample.class);

    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<>() {
            @Override
            public void accept(String s) {
                log.info("value: {}", s);
            }
        };

        consumer.accept("string");
    }
}
