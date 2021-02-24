package ru.demi.java11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class LocalVarForLambdaExample {
    private static final Logger log = LoggerFactory.getLogger(LocalVarForLambdaExample.class);

    public static void main(String[] args) {
        List.of(1, 2, 3).forEach((var i) -> log.info("value: {}", i));

        Map.ofEntries(
            Map.entry(1, "one"),
            Map.entry(2, "two"),
            Map.entry(3, "three")
        )
        .forEach((var key, var value) -> log.info("key: {}, value: {}", key, value));
    }
}
