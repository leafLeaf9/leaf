package com.gousade.java8.lambda;

import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
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
/*
 * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 * 
 * 1. 对象的引用 :: 实例方法名
 * 
 * 2. 类名 :: 静态方法名
 * 
 * 3. 类名 :: 实例方法名
 * 
 * 注意：
 * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 * 
 * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 * 
 * 1. 类名 :: new
 * 
 * 三、数组引用
 * 
 * 	类型[] :: new;
 * 
 * 
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
	public void test22() {
		Employee employee = new Employee(0, "Tom", 16, 9999.9, null);
		Supplier<String> supplier = () -> SaltUtil.generateUUId();
		Supplier<String> supplier2 = SaltUtil::generateUUId;
		Supplier<String> supplier3 = () -> employee.getName();
		Supplier<String> supplier4 = employee::getName;
//		Supplier<String> supplier5 = Employee::getName;//报错
	}
	
	@Test
	public void test222() {
		Employee employee = new Employee(0, "Tom", 16, 9999.9, null);
		Function<Employee, String> function = e -> e.getName();
		System.out.println(function.apply(employee));
		System.out.println("--------------------------");
		Function<Employee, String> function2 = Employee::getName;
		System.out.println(function2.apply(employee));
	}

	@Test
	public void test3() {
		BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);
		System.out.println(biPredicate.test("abc", "abcd"));
		System.out.println("--------------------------");
		BiPredicate<String, String> biPredicate2 = String::equals;
		System.out.println(biPredicate2.test("abc", "abc"));
	}

	//构造器引用
	@Test
	public void test4() {
		Supplier<Gift> supplier = () -> new Gift();
		Supplier<Gift> supplier2 = Gift::new;
	}

	// 数组引用
	@Test
	public void test5() {
		Function<Integer, String[]> fun = (args) -> new String[args];
		String[] strs = fun.apply(10);
		System.out.println(strs.length);

		System.out.println("--------------------------");

		Function<Integer, Employee[]> fun2 = Employee[]::new;
		Employee[] emps = fun2.apply(20);
		System.out.println(emps.length);
	}

}
