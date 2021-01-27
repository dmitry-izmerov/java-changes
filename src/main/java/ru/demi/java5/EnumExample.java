package ru.demi.java5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnumExample {
    private static final Logger log = LoggerFactory.getLogger(EnumExample.class);

    public static void main(String[] args) {
        log.info("is Enum class parent for STATUS? - {}", Enum.class.isAssignableFrom(FRUIT.class));

        for (var s : FRUIT.values()) {
            log.info("desc for {} = {}", s, s.getDescription());
        }
    }
}

enum FRUIT {
    KIWI(COLOR.GREEN, 53, 77),
    APPLE(COLOR.RED, 66, 45),
    MELON(COLOR.WHITE, 89, 21),
    ORANGE(COLOR.ORANGE, 72, 55),
    BANANA(COLOR.YELLOW, 76, 34);

    private final COLOR color;
    private final int calories;
    private final int usefulness; // in scale from 0 to 100

    FRUIT(COLOR color, int calories, int usefulness) {
        this.color = color;
        this.calories = calories;
        this.usefulness = usefulness;
    }

    String getDescription() {
        return String.format("Color: %s, calories: %d, usefulness: %d", color, calories, usefulness);
    }
}

enum COLOR {
    GREEN,
    ORANGE,
    RED,
    WHITE,
    YELLOW
}
