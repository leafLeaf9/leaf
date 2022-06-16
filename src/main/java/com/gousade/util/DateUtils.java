package com.gousade.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author woxigousade
 * @date 2020/7/21
 */
public class DateUtils {
    public static final ZoneId CTT = ZoneId.of("Asia/Shanghai");
    public static final String dateFormatter = "yyyy-MM-dd HH:mm:ss";
    public static final String dayFormatter = "yyyy-MM-dd";

    public static Date zonedDateTimeToDate(ZonedDateTime zonedDateTime) {
        return Date.from(zonedDateTime.toInstant());
    }

    public static ZonedDateTime dateToZonedDateTime(Date date) {
        String formattedDate = formatTime(date);
        return dateTimeStrToZonedDateTime(formattedDate);
    }

    public static String formatTime(Date date) {
        return formatTime(date, dateFormatter);
    }

    public static String formatTime(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static String formatTime(ZonedDateTime zonedDateTime) {
        return formatTime(zonedDateTime, dateFormatter);
    }

    public static String formatZonedDateTimeToDay(ZonedDateTime zonedDateTime) {
        return formatTime(zonedDateTime, dayFormatter);
    }

    public static String formatTime(ZonedDateTime zonedDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withZone(CTT);
        return zonedDateTime.format(formatter);
    }

    public static ZonedDateTime dateTimeStrToZonedDateTime(String dateStr) {
        return dateTimeStrToZonedDateTime(dateStr, dateFormatter);
    }

    public static ZonedDateTime dateTimeStrToZonedDateTime(String dateStr, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withZone(CTT);
        return ZonedDateTime.parse(dateStr, formatter);
    }

    public static ZonedDateTime dateStrToZonedDateTime(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dayFormatter).withZone(CTT);
        LocalDate localDate = LocalDate.parse(dateStr, formatter);
        return localDate.atStartOfDay(CTT);
    }

    public static LocalDate dateToLocalDate(Date date) {
        ZonedDateTime zonedDateTime = dateToZonedDateTime(date);
        return zonedDateTime.toLocalDate();
    }

    public static LocalTime timeStrToLocalTime(String timeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss").withZone(CTT);
        return LocalTime.parse(timeStr, formatter);
    }

    public static String zeroComplete(int time) {
        return time < 10 ? "0" + time : String.valueOf(time);
    }

    public static String zeroComplete(long time) {
        return time < 10 ? "0" + time : String.valueOf(time);
    }

    public static List<LocalDate> buildMonthDays(ZonedDateTime time) {
        List<LocalDate> result = new ArrayList<>();
        ZonedDateTime startTime = time.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
        ZonedDateTime endTime = startTime.plusMonths(1);
        while (startTime.isBefore(endTime)) {
            result.add(startTime.toLocalDate());
            startTime = startTime.plusDays(1);
        }
        return result;
    }

    /**
     * 构造00:00 到 24:00 (5分钟间隔)的集合
     */
    public static List<String> buildFiveMinuteSummaryTimes() {
        List<String> result = new ArrayList<>();
        ZonedDateTime startTime = ZonedDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        ZonedDateTime endTime = startTime.plusDays(1).withMinute(0);
        while (startTime.isBefore(endTime)) {
            result.add(DateUtils.formatTime(startTime, "HH:mm"));
            startTime = startTime.plusMinutes(5);
        }
        result.add("24:00");
        return result;
    }

    public static List<String> buildDaySummaryTimes(int splitMinute) {
        List<String> result = new ArrayList<>();
        ZonedDateTime startTime = ZonedDateTime.now().with(LocalTime.MIN);
        ZonedDateTime endTime = startTime.plusDays(1);
        //第一个时刻从00:00 + splitMinute开始
        startTime = startTime.plusMinutes(splitMinute);
        while (startTime.isBefore(endTime)) {
            result.add(DateUtils.formatTime(startTime, "HH:mm"));
            startTime = startTime.plusMinutes(splitMinute);
        }
        result.add("24:00");
        return result;
    }

    public static ZonedDateTime getRecentFiveMinuteTime() {
        ZonedDateTime now = ZonedDateTime.now();
        int serialNumber = java.lang.Math.toIntExact(java.lang.Math.round(now.getMinute() / 5.0));
        //withMinute参数不能为60
        return now.withMinute(0).plusMinutes(serialNumber * 5L).withSecond(0).withNano(0);
    }
}
