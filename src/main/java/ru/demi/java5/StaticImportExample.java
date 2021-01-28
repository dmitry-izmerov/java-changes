package ru.demi.java5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Month;

import static java.time.Month.JANUARY;

public class StaticImportExample {
    private static final Logger log = LoggerFactory.getLogger(StaticImportExample.class);

    public static void main(String[] args) {
        // usual reference to static member by parent class
        log.info("the coolest month is {}", Month.JULY);
        // you can use JANUARY instead of Month.JANUARY since Java 5
        log.info("the coldest month is {}", JANUARY);
    }
}
