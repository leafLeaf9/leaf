package com.gousade.lambda;

import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import com.gousade.entity.Gift;
import com.gousade.utils.SaltUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author woxigsd@gmail.com
 * @date 2020-10-23 14:40:57
 * @description 
 */
@Slf4j
@SuppressWarnings("unused")
public class LambdaMethodRefrenceTest {
	
	@Test
	public void testMethodRefrence() {
		Consumer<String> consumer = (x) -> System.out.println(x);
		Consumer<Gift> consumer2 = System.out::println;
		Consumer<String> consumer3 = log::info;
		consumer3.accept("zeze");
//		Consumer<Gift> consumer4 = log::info;//log中没有info(Gift t)方法
	}
	
	@Test
	public void test2() {
		Supplier<String> supplier = () -> SaltUtil.generateUUId();
		Supplier<String> supplier2 = SaltUtil::generateUUId;
	}
	
	@Test
	public void test3() {
		BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);
		BiPredicate<String, String> biPredicate2 = String::equals;
	}
	
	@Test
	public void test4() {
		Supplier<Gift> supplier = () -> new Gift();
		Supplier<Gift> supplier2 = Gift::new;
	}

}
