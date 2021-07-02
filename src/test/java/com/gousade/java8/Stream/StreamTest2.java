package com.gousade.java8.Stream;

import com.gousade.entity.Gift;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author woxigsd@gmail.com
 * @date 2020-10-26 11:02:20
 * @description 中间操作
 */
/*
筛选与切片
	filter——接收 Lambda ， 从流中排除某些元素。
	limit——截断流，使其元素不超过给定数量。
	skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
	distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
*/

@Slf4j
public class StreamTest2 {

    List<Gift> giftList = Arrays.asList(new Gift(1, "gift1", 0.1), new Gift(3, "gift3", 0.3), new Gift(2, "gift2", 0.2),
            new Gift(4, "gift4", 0.4), new Gift(55, "gift55", 0.15), new Gift(55, "gift55", 0.15),
            new Gift(55, "gift55", 0.15), new Gift(55, "gift55", 0.15));

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    @Test
    public void test1() {
        Stream<Gift> stream = giftList.stream().filter((e) -> {
            System.out.println("中间操作");
            return e.getProb() > 0.1 && e.getProb() < 0.4;
        });
        // 只有触发终止操作时才会处理数据
        stream.forEach(System.out::println);
    }

    @Test
    public void test2() {
        giftList.stream()/*.limit(2)*/.filter((e) -> {
            System.out.println("得到2个符合条件的数据之后不再迭代");
            return e.getProb() > 0.1 && e.getProb() < 0.4;
        }).limit(2).forEach(System.out::println);
    }

	/*
	映射
	map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
	flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
	*/

    @Test
    public void test3() {
        giftList.stream().filter((e) -> {
            System.out.println("得到2个符合条件的数据之后不再迭代");
            return e.getProb() > 0.1 && e.getProb() < 0.4;
        }).skip(2).distinct().forEach(System.out::println);
    }

    @Test
    public void test4() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        list.stream().map((str) -> str.toUpperCase())/*.map(String::toUpperCase)*/.forEach(System.out::println);
        System.out.println("---------------------------------------------");
        giftList.stream().map(Gift::getName).forEach(log::info);
        System.out.println("---------------------------------------------");
        list.stream().map(StreamTest2::filterCharacter).forEach((item) -> {
            item.forEach(System.out::println);
        });
        System.out.println("---------------------------------------------");
        list.stream().flatMap(StreamTest2::filterCharacter).forEach(System.out::println);
    }

    /**
     * sorted()——自然排序 sorted(Comparator com)——定制排序
     */
    @Test
    public void test5() {
        List<String> list = Arrays.asList("ddd", "bbb", "eee", "aaa", "ccc");
        list.stream().sorted().forEach(System.out::println);
//		giftList.stream().sorted().forEach(System.out::println);//Gift类没有实现Comparable接口，会报错
        giftList.stream().sorted((item1, item2) -> {
            if (Double.doubleToRawLongBits(item1.getProb()) == Double.doubleToLongBits(item2.getProb())) {
                return item1.getName().compareTo(item2.getName());
            } else {
                return item1.getProb().compareTo(item2.getProb());
            }
        }).forEach(System.out::println);
    }

    /**
     * stream 将list里的对象根据指定成员变量进行分组
     */
    @Test
    public void groupTest() {
        Map<Double, List<Gift>> probMap = giftList.stream()
                .collect(Collectors.groupingBy(Gift::getProb));
        probMap.forEach((k, v) -> System.out.println(v));
    }

}
