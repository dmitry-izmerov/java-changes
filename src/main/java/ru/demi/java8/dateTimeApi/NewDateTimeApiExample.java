package ru.demi.java8.dateTimeApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.JapaneseDate;
import java.time.chrono.JapaneseEra;
import java.time.format.DateTimeFormatter;

public class NewDateTimeApiExample {
    private static final Logger log = LoggerFactory.getLogger(NewDateTimeApiExample.class);

    public static void main(String[] args) {
        var localDate = LocalDate.of(2020, 1, 1);
        var dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        log.info(dateFormatter.format(localDate));

        var localDateTime = LocalDateTime.now().minusDays(2).plusMonths(2);
        log.info(DateTimeFormatter.ISO_DATE_TIME.format(localDateTime));

        var japaneseDate = JapaneseDate.of(JapaneseEra.HEISEI, 7, 1, 1);
        log.info("japanese date: {}", DateTimeFormatter.ofPattern("G dd-MM-yy").format(japaneseDate));

        var dateString = "14-02-1924";
        var parsed = LocalDate.parse(dateString, dateFormatter);
    }
}
