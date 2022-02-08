package com.gousade.java8.time;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * @author woxigsd@gmail.com
 * @date 2020-10-29 11:14:12
 * @description
 */
public class DateTimeTest {

    // 1. LocalDate、LocalTime、LocalDateTime
    @Test
    public void test1() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ld2 = LocalDateTime.of(2020, 10, 29, 10, 10, 10);
        System.out.println(ld2);

        LocalDateTime ldt3 = ld2.plusYears(20);
        System.out.println(ldt3);

        LocalDateTime ldt4 = ld2.minusMonths(2);
        System.out.println(ldt4);

        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonth());
        System.out.println(ldt.getMonthValue());
//		System.out.println(ldt.getDayOfYear());
        System.out.println(ldt.getDayOfMonth());
        System.out.println(ldt.getHour());
        System.out.println(ldt.getMinute());
        System.out.println(ldt.getSecond());
    }

    // 2. Instant : 时间戳。 （使用 Unix 元年 1970年1月1日 00:00:00 所经历的毫秒值）
    @Test
    public void test2() {
        Instant ins = Instant.now(); // 默认使用 UTC 时区
        System.out.println(ins);

        OffsetDateTime odt = ins.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);

        System.out.println(ins.toEpochMilli());

        Instant ins2 = Instant.ofEpochSecond(5);
        System.out.println(ins2);
    }

    // 3.
    // Duration : 用于计算两个“时间”间隔
    // Period : 用于计算两个“日期”间隔
    @Test
    public void test3() {
        Instant ins1 = Instant.now();

        System.out.println("--------------------");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        Instant ins2 = Instant.now();

        System.out.println("所耗费时间为：" + Duration.between(ins1, ins2).toMillis());

        System.out.println("----------------------------------");

        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(2011, 1, 1);
        Period pe = Period.between(ld1, ld2);
        System.out.println(pe.getYears());
        System.out.println(pe.getMonths());
        System.out.println(pe.getDays());
    }

    // 4. TemporalAdjuster : 时间校正器
    @Test
    public void test4() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);

        // 自定义：下一个工作日
        LocalDateTime ldt5 = ldt.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;

            DayOfWeek dow = ldt4.getDayOfWeek();

            if (dow.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dow.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });

        System.out.println(ldt5);

    }

    @Test
    public void testTemporalAdjusters() {
        LocalDate nowDate = LocalDate.now().minusYears(1);
        LocalDate firstDayOfYear = nowDate.with(TemporalAdjusters.firstDayOfYear());
        LocalDate lastDayOfYear = nowDate.with(TemporalAdjusters.lastDayOfYear());
        LocalDateTime firstDateOfYear = LocalDateTime.of(firstDayOfYear, LocalTime.MIN);
        LocalDateTime lastDateOfYear = LocalDateTime.of(lastDayOfYear, LocalTime.MAX);

        LocalDate firstDayOfMonth = nowDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = nowDate.with(TemporalAdjusters.lastDayOfMonth());
        LocalDateTime firstDateOfMonth = LocalDateTime.of(firstDayOfMonth, LocalTime.MIN);
        LocalDateTime lastDateOfMonth = LocalDateTime.of(lastDayOfMonth, LocalTime.MAX);

        LocalDateTime fistDateOfDay = LocalDateTime.of(nowDate, LocalTime.MIN);
        LocalDateTime lastDateOfDay = LocalDateTime.of(nowDate, LocalTime.MAX);


        System.out.println("当前时间：" + LocalDateTime.now());

        System.out.println("今年第一天：" + firstDateOfYear);
        System.out.println("今天最后一天：" + lastDateOfYear);

        System.out.println("当月第一天：" + firstDateOfMonth);
        System.out.println("当月最后一天：" + lastDateOfMonth);

        System.out.println("当天开始时间：" + fistDateOfDay);
        System.out.println("当天结束时间：" + lastDateOfDay);
//        print：
//        当前时间：2020-04-17T10:10:40.443
//        今年第一天：2020-01-01T00:00
//        今天最后一天：2020-12-31T23:59:59.999999999
//        当月第一天：2020-04-01T00:00
//        当月最后一天：2020-04-30T23:59:59.999999999
//        当天开始时间：2020-04-17T00:00
//        当天结束时间：2020-04-17T23:59:59.999999999
    }

    @Test
    public void testTemporalAdjusters1() {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
        ZonedDateTime firstDayOfMonth0 = now.with(TemporalAdjusters.firstDayOfMonth())
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
        System.out.println(now);
        System.out.println(firstDayOfMonth);
        System.out.println(firstDayOfMonth0);
    }


    // 5. DateTimeFormatter : 解析和格式化日期或时间
    @Test
    public void test5() {
//			DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E");

        LocalDateTime ldt = LocalDateTime.now();
        String strDate = ldt.format(dtf);

        System.out.println(dtf.format(ldt));
        System.out.println(strDate);

        LocalDateTime newLdt = LocalDateTime.parse(strDate, dtf);
        System.out.println(newLdt);
    }

    @Test
    public void test6() {
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }

    // 6.ZonedDate、ZonedTime、ZonedDateTime ： 带时区的时间或日期
    @Test
    public void test7() {
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt);

        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("US/Pacific"));
        System.out.println(zdt);
    }

    @Test
    public void test9() {
        System.out.println(LocalDateTime.now());
        System.out.println(ZonedDateTime.now());
        ZonedDateTime zdt = ZonedDateTime.now();
        ZonedDateTime nexTime = zdt.with((l) -> {
            ZonedDateTime ldt4 = (ZonedDateTime) l;

            DayOfWeek dow = ldt4.getDayOfWeek();

            if (dow.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dow.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });
        System.out.println(nexTime);
        String currentTime = ZonedDateTime.now(/*ZoneId.of("Asia/Shanghai")*/)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS ZZZZ E 'CST'"));
        System.out.println("Current Time : " + currentTime);
    }

}
