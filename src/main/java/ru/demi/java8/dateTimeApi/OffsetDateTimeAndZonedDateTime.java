package ru.demi.java8.dateTimeApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;

public class OffsetDateTimeAndZonedDateTime {
    private static final Logger log = LoggerFactory.getLogger(OffsetDateTimeAndZonedDateTime.class);

    public static void main(String[] args) {
        // adds to the instant the offset from UTC/Greenwich
        // preferable for working with database
        var offsetDateTime = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.ofHours(2));
        log.info("offset date time: {}", offsetDateTime);

        // adds full time-zone rules, considers switching between summer/winter timer
        var zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Europe/Paris"));
        log.info("zoned date time: {}", zonedDateTime);
    }
}
