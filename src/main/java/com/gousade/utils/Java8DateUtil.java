package com.gousade.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author woxigousade
 * @date 2020/7/21
 */
public class Java8DateUtil {
    public static final ZoneId CTT = ZoneId.of("Asia/Shanghai");

    public static Date zonedDateTimeToDate(ZonedDateTime zonedDateTime) {
        return Date.from(zonedDateTime.toInstant());
    }

    public static ZonedDateTime dateToZonedDateTime(Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), CTT);
    }

    public static String formatZonedDateTime(ZonedDateTime zonedDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(CTT);
        return zonedDateTime.format(formatter);
    }

    public static ZonedDateTime dateTimeStrToZonedDateTime(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(CTT);
        return ZonedDateTime.parse(dateStr, formatter);
    }

    public static ZonedDateTime dateTimeStrToZonedDateTime(String dateStr, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withZone(CTT);
        return ZonedDateTime.parse(dateStr, formatter);
    }

    public static ZonedDateTime dateStrToZonedDateTime(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(CTT);
        LocalDate localDate = LocalDate.parse(dateStr, formatter);
        return localDate.atStartOfDay(CTT);
    }
}
