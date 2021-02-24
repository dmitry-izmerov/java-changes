package ru.demi.java12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JvmConstantApiExample {
    private static final Logger log = LoggerFactory.getLogger(JvmConstantApiExample.class);

    public static void main(String[] args) {
        int.class.describeConstable()
            .ifPresent(classDesc -> log.info("primitive class desc: {}", classDesc));

        int[].class.describeConstable()
            .ifPresent(classDesc -> log.info("array class desc: {}", classDesc));

        String.class.describeConstable()
            .ifPresent(classDesc -> log.info("object class desc: {}", classDesc));
    }
}
