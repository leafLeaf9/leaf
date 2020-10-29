package com.gousade.java8.Optional;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.gousade.java8.lambda.Employee;
import com.gousade.java8.lambda.Employee.Status;

/**
 * @author woxigsd@gmail.com
 * @date 2020-10-28 10:47:09
 * @description
 */
/*
 * 一、Optional 容器类：用于尽量避免空指针异常
 * 	Optional.of(T t) : 创建一个 Optional 实例
 * 	Optional.empty() : 创建一个空的 Optional 实例
 * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * 	isPresent() : 判断是否包含值
 * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 */
public class OptionalTest {

	@Test
	public void test1() {
		Optional<Employee> optional = Optional.of(new Employee());
		System.out.println(optional.get());
	}

	@Test
	public void test2() {
		Optional<Employee> op = Optional.ofNullable(null);// return value == null ? empty() : of(value);
		System.out.println(op/*.get()*/);

		Optional<Employee> op2 = Optional.empty();
		System.out.println(op2.get());
	}

	@Test
	public void test3() {
		Optional<Employee> op = Optional.ofNullable(new Employee());

		if (op.isPresent()) {
			System.out.println(op.get());
		}

		Employee emp = op.orElse(new Employee(0, "张三", 0, 0, null));// return value != null ? value : other;
		System.out.println(emp);

		Employee emp2 = op.orElseGet(() -> new Employee());
		System.out.println(emp2);
	}

	@Test
	public void test4() {
		Optional<Employee> op = Optional.of(new Employee(101, "张三", 18, 9999.99, Status.FREE));

		Optional<String> op2 = op.map(Employee::getName);
		System.out.println(op2.get());

		Optional<String> op3 = op.flatMap((e) -> Optional.of(e.getName()));
		System.out.println(op3.get());
	}

}
