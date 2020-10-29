package com.gousade.java8.Stream;

/**
 * @author woxigsd@gmail.com
 * @date 2020-10-26 10:26:35
 * @description 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import com.gousade.entity.Gift;

/*
 * 一、Stream API 的操作步骤：
 * 
 * 1. 创建 Stream
 * 
 * 2. 中间操作
 * 
 * 3. 终止操作(终端操作)
 */
@SuppressWarnings("unused")
public class StreamTest1 {

	/**
	 * 创建Stream的多种方式 1. Collection 提供了两个方法 stream() 与 parallelStream() 2.通过 Arrays
	 * 中的 stream() 获取一个数组流 3. 通过 Stream 类中静态方法 of() 4. 创建无限流
	 */
	@Test
	public void test1() {
		List<String> list = new ArrayList<>();
		Stream<String> stream = list.stream();

		Gift[] gifts = new Gift[10];
		Stream<Gift> stream2 = Arrays.stream(gifts);

		Stream<Object> stream3 = Stream.of("aa", 11, "bb");

		/*Stream<Integer> stream4 =*/ Stream.iterate(0, (x) -> x + 2).limit(10).forEach(System.out::println);

		Stream.generate(() -> Math.random()).limit(10).forEach(System.out::println);
	}

}
