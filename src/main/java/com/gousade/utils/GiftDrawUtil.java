package com.gousade.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import com.gousade.entity.Gift;

import lombok.extern.slf4j.Slf4j;

/**
 * @author woxigsd@gmail.com
 * @date 2020-10-14 14:36:59
 * @description 礼物概率抽奖工具
 * @see https://blog.csdn.net/yyc252532009/article/details/75950120
 */
@Slf4j
public class GiftDrawUtil {

	@Test
	public void random() {
		List<Gift> list = new ArrayList<>();
		list.add(new Gift(1, "gift1", 0.1));
		list.add(new Gift(3, "gift3", 0.3));
		list.add(new Gift(2, "gift2", 0.2));
		list.add(new Gift(4, "gift4", 0.4));
		list.sort((item1, item2) -> item1.getProb().compareTo(item2.getProb()));
		/*list.sort((item1, item2) -> {
			log.info("item1={}, item2={}", item1, item2);
			return item1.getProb().compareTo(item2.getProb());
		});*/
		list.forEach(System.out::println);
		list.stream().filter((e) -> e.getId() > 1).limit(2).forEach(System.out::println);
		list.stream().map(Gift::getName).forEach(item -> log.info(item));
		Map<String, Integer> countMap = new LinkedHashMap<>();
		list.forEach(item -> {
			countMap.put(item.getName(), 0);
		});
		countMap.put("null", 0);
		for (int i = 0; i < 999999; i++) {
			Gift gift = draw(list);
			String name = "null";
			if (gift != null) {
				name = gift.getName();
			}
			int count = countMap.get(name);
			countMap.put(gift.getName(), ++count);
		}
		countMap.forEach((k, v) -> {
			log.info("Draw {} {} times.", k, v);
		});
		System.out.println(countMap.toString());
	}

	public Gift draw(List<Gift> list) {
//		log.info("random gift start.");
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
//        log.info("list={}",list);
//		list.sort((item1, item2) -> item1.getProb().compareTo(item2.getProb()));
		list.sort((item1, item2) -> {
//			log.info("item1={}, item2={}", item1, item2);
			return item1.getProb().compareTo(item2.getProb());
		});
//        log.info("list={}",list);
		List<Double> probLists = new ArrayList<>(list.size());
//        Double sumProb = 0D;
		Double[] sumProbArr = new Double[] { 0D };
		list.forEach((item) -> {
			sumProbArr[0] += item.getProb();
		});
		if (sumProbArr[0] <= 0) {
			return null;
		}
//        归一化概率端点，因为可能给出的概率加起来不等于1，这里换算为总和为1
//        Double rate = 0D;
		Double[] rateArr = new Double[] { 0D };
		list.forEach((item) -> {
			rateArr[0] += item.getProb();// 相当于0.1/1 0.3/1 0.6/1 1.0/1 得到一个数轴区间
//        	probLists.add(rateArr[0]/sumProbArr[0]);
			probLists.add(BigDecimalCalculator.divide(rateArr[0], sumProbArr[0]));
		});
		double random = Math.random();
		probLists.add(random);
//		Collections.sort(probLists);
		probLists.sort(null);
		int index = probLists.indexOf(random);
		if (index >= 0) {
//            log.info("{}",list.get(index).getName());
			return list.get(index);
		}
		return null;
	}

}
