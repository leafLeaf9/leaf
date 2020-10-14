package com.gousade.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.gousade.entity.Gift;

import lombok.extern.slf4j.Slf4j;

/**
 * @author woxigsd@gmail.com
 * @date 2020-10-14 14:36:59
 * @description 礼物概率抽奖工具
 * {@link https://blog.csdn.net/yyc252532009/article/details/75950120}
 */
@Slf4j
public class GiftDrawUtil {
	
	@Test
	public void random() {
		int[] count = new int[] {0,0,0,0};
		for(int i =0;i<999999;i++) {
			String result = test();
			switch(result) {
			case "gift1": count[0]++;break;
			case "gift2": count[1]++;break;
			case "gift3": count[2]++;break;
			case "gift4": count[3]++;break;
			}
		}
		log.info("result is {}",Arrays.toString(count));
	}
	
	@Test
	public String test() {
//		log.info("random gift start.");
		List<Gift> list = new ArrayList<>();
        list.add(new Gift(1,"gift1",0.1));
        list.add(new Gift(3,"gift3",0.3));
        list.add(new Gift(2,"gift2",0.2));
        list.add(new Gift(4,"gift4",0.4));
//        log.info("list={}",list);
        list.sort((item1,item2)->item1.getProb().compareTo(item2.getProb()));
//        log.info("list={}",list);
        List<Double> probLists = new ArrayList<>(list.size());
//        Double sumProb = 0D;
        Double [] sumProbArr = new Double[] {0D};
        list.forEach((item)->{
        	sumProbArr[0] += item.getProb();
        	});
//        	归一化概率端点，因为可能给出的概率加起来不等于1，这里换算为总和为1
//        Double rate = 0D;
        Double [] rateArr = new Double[] {0D};
        list.forEach((item)->{
        	rateArr[0] += item.getProb();//相当于0.1/1 0.3/1 0.6/1 1.0/1 得到一个数轴区间 
//        	probLists.add(rateArr[0]/sumProbArr[0]);
        	probLists.add(BigDecimalCalculator.divide(rateArr[0], sumProbArr[0]));
        	});
        double random = Math.random();
        probLists.add(random);
        Collections.sort(probLists);
        int index = probLists.indexOf(random);
        if (index >= 0) {
//            log.info("{}",list.get(index).getName());
            return list.get(index).getName();
        }
        return "";
	}

}
