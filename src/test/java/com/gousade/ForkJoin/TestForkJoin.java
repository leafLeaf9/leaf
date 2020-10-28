package com.gousade.ForkJoin;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author woxigsd@gmail.com
 * @date 2020-10-28 9:33:14
 * @description
 */
@Slf4j
public class TestForkJoin {

	@Test
	public void test1() {
		Instant start = Instant.now();
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		ForkJoinTask<Long> task = new ForkJoinCalculate(0L, 100000000000L);
		
		long sum = forkJoinPool.invoke(task);
		System.out.println(sum);
		Instant end = Instant.now();
		log.info("耗费时间为{}毫秒", Duration.between(start, end).toMillis());
	}
	
	@Test
	public void test2(){
		long start = System.currentTimeMillis();
		Instant start2 = Instant.now();
		long sum = 0L;
		
		for (long i = 0L; i <= 100000000000L; i++) {
			sum += i;
		}
		
		System.out.println(sum);
		
		long end = System.currentTimeMillis();
		Instant end2 = Instant.now();
		System.out.println("耗费的时间为: " + (end - start));
		log.info("耗费时间为{}毫秒", Duration.between(start2, end2).toMillis());
	}
	
	@Test
	public void test3(){
		Instant start = Instant.now();
		long sum = LongStream.rangeClosed(0, 100000000000L).parallel().reduce(0, Long::sum);
		System.out.println(sum);
		Instant end = Instant.now();
		log.info("耗费时间为{}毫秒", Duration.between(start, end).toMillis());
	}

}
