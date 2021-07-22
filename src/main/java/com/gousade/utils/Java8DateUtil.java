package com.gousade.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author woxigousade
 * @date 2020/7/21
 */
public class Java8DateUtil {
    public static Date ZonedDateTimeToDate(ZonedDateTime zonedDateTime) {
        return Date.from(zonedDateTime.toInstant());
    }

    public static ZonedDateTime DateToZonedDateTime(Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("Asia/Shanghai"));
    }

    public static String formatZonedDateTime(ZonedDateTime zonedDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.of("Asia/Shanghai"));
        return zonedDateTime.format(formatter);
    }

    public static ZonedDateTime DateStrToZonedDateTime(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.of("Asia/Shanghai"));
        return ZonedDateTime.parse(dateStr, formatter);
    }
}
