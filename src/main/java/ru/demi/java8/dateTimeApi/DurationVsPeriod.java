package ru.demi.java8.dateTimeApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class DurationVsPeriod {
    private static final Logger log = LoggerFactory.getLogger(DurationVsPeriod.class);

    public static void main(String[] args) {
        // Duration for time-based values - seconds, nanoseconds
        var duration = Duration.ofHours(1);
        log.info("duration in sec: {}", duration.toSeconds());
        var date1 = LocalDateTime.of(2021, 1, 1, 0, 0);
        var date2 = LocalDateTime.of(2021, 3, 1, 0, 0);
        log.info("duration between dates in days: {}", Duration.between(date1, date2).toDays());

        // Period for date-based values - years, months, days
        var period = Period.ofWeeks(5);
        log.info("duration in months: {}", period.toTotalMonths()); // will be 0
        var localDate1 = LocalDate.of(2021, 1, 1);
        var localDate2 = LocalDate.of(2021, 2, 2);
        log.info("period between dates - get months: {}", Period.between(localDate1, localDate2).getMonths());
        log.info("period between dates - get days: {}", Period.between(localDate1, localDate2).getDays());

        // use ChronoUnit if you want to get result in one unit of time, what is preferable choice
        log.info("diff between dates in days: {}", ChronoUnit.DAYS.between(localDate1, localDate2));
    }
}
