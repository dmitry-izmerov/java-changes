package ru.demi.java5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VarargsExample {
    private static final Logger log = LoggerFactory.getLogger(VarargsExample.class);

    public static void main(String[] args) {
        var res = methodWithVarargs("join parameters: ", "first", "second", "others");
        log.info(res);
    }

    // varargs should be in the end
    static String methodWithVarargs(String intro, String... params) {
        return intro + String.join(", ", params);
    }
}
