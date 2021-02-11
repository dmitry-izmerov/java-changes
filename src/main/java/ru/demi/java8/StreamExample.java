package ru.demi.java8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StreamExample {
    private static final Logger log = LoggerFactory.getLogger(StreamExample.class);

    public static void main(String[] args) {
        int i = 1;
        var list = List.of(
            new Account(i++, 450, "japan", Status.OPEN),
            new Account(i++, 200, "germany", Status.OPEN),
            new Account(i++, 120, "spain", Status.OPEN),
            new Account(i++, 230, "germany", Status.BLOCKED),
            new Account(i++, 440, "germany", Status.OPEN),
            new Account(i, 320, "spain", Status.OPEN)
        );

        var amountsPerCountry = list.stream()
            .filter(a -> Status.OPEN == a.status())
            .collect(Collectors.groupingBy(Account::country, TreeMap::new, Collectors.summingLong(Account::amount)));

        amountsPerCountry.forEach((key, value) -> log.info("country: {}, amount: {}", key, value));
    }

    static record Account(long id, long amount, String country, Status status) {}

    enum Status { OPEN, BLOCKED, CLOSED }
}
