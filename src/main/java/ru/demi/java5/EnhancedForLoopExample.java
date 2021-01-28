package ru.demi.java5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EnhancedForLoopExample {
    private static final Logger log = LoggerFactory.getLogger(EnhancedForLoopExample.class);

    public static void main(String[] args) {
        var list = List.of(1, 2, 3);
        // before java 5
        for (var iterator = list.iterator(); iterator.hasNext();) {
            log.info(String.valueOf(iterator.next()));
        }

        // after java 5
        for (var item : list) {
            log.info(String.valueOf(item));
        }
    }
}
