package ru.demi.java5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class AutoBoxingExample {

    private static final Logger log = LoggerFactory.getLogger(AutoBoxingExample.class);

    public static void main(String[] args) {
        var list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i += 2) {
            list.add(i); // autoboxing
        }

        for (int i = 1; i <= list.size(); i++) {
            int val = list.get(i - 1); // unboxing
            log.info("{}-th element = {}", i, val);
        }
    }
}
