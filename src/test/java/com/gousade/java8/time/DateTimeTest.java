package com.gousade.java8.time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.Test;

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
	}

}
