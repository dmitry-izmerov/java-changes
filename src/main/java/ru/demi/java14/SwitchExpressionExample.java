package ru.demi.java14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SwitchExpressionExample {
    private static final Logger log = LoggerFactory.getLogger(SwitchExpressionExample.class);

    public static void main(String[] args) {
        int num = switch (args[0]) {
            case "one", "One" -> 1;
            case "two", "Two" -> {
                yield 2;
            }
            default -> 0;
        };
        log.info("conversed num: {}", num);

        String mode = switch (args[1]) {
            case "H":
                yield "heating";
            case "C": {
                yield "cooling";
            }
            default:
                yield "default";
        };
        log.info("set mode: {}", mode);
    }
}
