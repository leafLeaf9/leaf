package com.gousade.Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.gousade.lambda.Employee;
import com.gousade.lambda.Employee.Status;

import lombok.extern.slf4j.Slf4j;


/**
 * @author woxigsd@gmail.com
 * @date 2020-10-27 9:49:18
 * @description 终止操作
 */
@Slf4j
public class StreamTest3 {
	
	List<Employee> employees = Arrays.asList(
			new Employee(102, "李四", 59, 6666.66, Status.FREE),
			new Employee(101, "张三", 18, 9999.99, Status.FREE),
			new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
			new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
			new Employee(104, "赵六", 8, 7777.77, Status.FREE),
			new Employee(104, "赵六", 8, 7777.77, Status.FREE),
			new Employee(105, "田七", 38, 5555.55, Status.BUSY)
	);
	
	/*
	allMatch——检查是否匹配所有元素
	anyMatch——检查是否至少匹配一个元素
	noneMatch——检查是否没有匹配的元素
	findFirst——返回第一个元素
	findAny——返回当前流中的任意元素
	count——返回流中元素的总个数
	max——返回流中最大值
	min——返回流中最小值
	*/
	@Test
	public void test1() {
		boolean b = employees.stream().allMatch((e) ->e.getStatus().equals(Status.BUSY));
		System.out.println(b);
		
		boolean b2 = employees.stream().anyMatch((e) ->e.getStatus().equals(Status.BUSY));
		System.out.println(b2);
		
		boolean b3 = employees.stream().noneMatch((e) ->e.getStatus().equals(Status.BUSY));
		System.out.println(b3);
		
		Optional<Employee> optional = employees.stream()
				.sorted((x, y) -> Double.compare(x.getSalary(), y.getSalary())).findFirst();
		System.out.println(optional.get());
		
		Optional<Employee> optional2 = employees.parallelStream()
				.filter((e) -> e.getStatus().equals(Status.FREE))
				.findAny();//findAny方法返回的结果很奇怪
			
			System.out.println(optional2.get());
	}
	
	@Test
	public void test2() {
		long count = employees.stream().filter((e) -> e.getStatus().equals(Status.FREE)).count();
		System.out.println(count);
		
		Optional<Employee> optional = employees.stream().max((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
		System.out.println(optional.get());
		
		Optional<Double> optional2 = employees.stream().map(/*e -> e.getSalary()*/Employee::getSalary).min(Double::compare);
		System.out.println(optional2.get());
	}
	
	/*
	归约
	reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
	 */
	@Test
	public void test3() {
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		Integer sum = list.stream().reduce(0, (x, y) -> x + y);
		System.out.println(sum);
		System.out.println("----------------------------------------");
		
		Optional<Double> optional = employees.stream().map(/*e->e.getSalary()*/Employee::getSalary)
			.reduce(/*(d1, d2) -> d1+d2*/Double::sum);
		System.out.println(optional.get());
	}
	
	/**
	 * 收集
	 * collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
	 */
	@Test
	public void test4() {
		List<String> list = employees.stream().map(Employee::getName).collect(Collectors.toList());
		list.forEach(log::info);
		System.out.println("----------------------------------");
		
		List<String> list2 = employees.stream().map(Employee::getName).distinct().collect(Collectors.toList());
		list2.forEach(log::info);
		System.out.println("----------------------------------");
		
		Set<String> list3 = employees.stream().map(Employee::getName).collect(Collectors.toSet());
		list3.forEach(log::info);
		System.out.println("----------------------------------");
		
		HashSet<String> list4 = employees.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
		list4.forEach(log::info);
		System.out.println("----------------------------------");
		
		List<Employee> list5 = employees.stream().collect(Collectors.toCollection(ArrayList<Employee>::new));
		list5.forEach(System.out::println);
		System.out.println("----------------------------------");
	}
	
	@Test
	public void test5() {
		//空闲员工总数
		Long count = employees.stream().filter((e) -> e.getStatus().equals(Status.FREE)).collect(Collectors.counting());
		System.out.println(count);
		System.out.println("----------------------------------");
		//工资平均值
		Double average = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
		System.out.println(average);
		System.out.println("----------------------------------");
		//工资总和
		Double sum = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
		System.out.println(sum);
		System.out.println("----------------------------------");
		//工资总和另一种写法
		Optional<Double> optionalSum = employees.stream().map(Employee::getSalary).collect(Collectors.reducing(Double::sum));
		System.out.println(optionalSum);
		System.out.println("----------------------------------");
		//工资最大值的员工信息
		Optional<Employee> optionalMax = employees.stream()
				.collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
		System.out.println(optionalMax.get());
		System.out.println("----------------------------------");
		//工资最大值的员工信息 另一种写法
				Optional<Employee> optionalMax2 = employees.stream()
						.max((e1, e2)-> Double.compare(e1.getSalary(), e2.getSalary()));
				System.out.println(optionalMax2.get());
				System.out.println("----------------------------------");
		//工资最小值
		Optional<Double> optionalMin = employees.stream().map(Employee::getSalary)
				.collect(Collectors.minBy(Double::compareTo));
		System.out.println(optionalMin.get());
		System.out.println("----------------------------------");
		//工资总和、最大值、平均值
		DoubleSummaryStatistics dss = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println(dss.getSum());
		System.out.println(dss.getMax());
		System.out.println(dss.getAverage());
		System.out.println("----------------------------------");
	}
	
	/**
	 * 分组
	 */
	@Test
	public void test6() {
		Map<Status, List<Employee>> map = employees.stream()
				.collect(Collectors.groupingBy(/*e->e.getStatus()*/Employee::getStatus));
		System.out.println(map);
	}
	
	/**
	 * 多级分组
	 */
	@Test
	public void test7() {
		Map<Status, Map<String, List<Employee>>> map = employees.stream()
				.collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
					if(e.getAge()<=35) {
						return "青年";
					}else if(e.getAge()<=50) {
						return "中年";
					}else {
						return "老年";
						}
				})));
		System.out.println(map);
	}
	
	/**
	 * 分区 true区和false区
	 */
	@Test
	public void test8() {
		Map<Boolean, List<Employee>> map = employees.stream()
				.collect(Collectors.partitioningBy((e) -> e.getSalary()>8000));
		System.out.println(map);
	}
	
	@Test
	public void test9() {
		String string = employees.stream().map(Employee::getName).collect(Collectors.joining());
		log.info(string);
		String string2 = employees.stream().map(Employee::getName).collect(Collectors.joining(",", "+++", "---"));
		log.info(string2);
	}

}
