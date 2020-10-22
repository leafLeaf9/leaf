package com.gousade.lambda;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author woxigsd@gmail.com
 * @date 2020-10-22 14:49:23
 * @description Lambda
 */

import org.junit.jupiter.api.Test;

import com.gousade.entity.Gift;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LambdaTest {

	@Test
	public void test1() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				log.info("normal runable");
			}
		};
		runnable.run();
		log.info("--------------");
		Runnable runnable2 = () -> log.info("lambda runable for void run()");
		runnable2.run();
	}

	@Test
	public void test2() {
		Consumer<Gift> consumer = (e) -> {
			e.setId(e.getId() + 10);
			log.info(e.toString());
		};
		consumer.accept(new Gift(1, "gift1", 0.1));
		Consumer<String> consumer2 = (e) -> log.info(e);
		consumer2.accept("lambda for void accept(T t)");
	}

	@Test
	public void test3() {
		Comparator<Integer> comparator = (x, y) -> {
			log.info("x={}, y={}", x, y);
			return x.compareTo(y);
		};
		log.info("result = {}", comparator.compare(9, 20));
		Comparator<Integer> comparator2 = (x, y) -> x.compareTo(y);
		log.info("result = {}", comparator2.compare(20, 9));
	}

	public Integer operation(Integer o1, Integer o2, Comparator<Integer> comparator) {
		return comparator.compare(o1, o2);
	}

	@Test
	public void test4() {
		log.info("operation * result = {}", operation(100, 200, (o1, o2) -> o1 * o2));
		log.info("operation + result = {}", operation(100, 200, (o1, o2) -> o1 + o2));
	}

}
