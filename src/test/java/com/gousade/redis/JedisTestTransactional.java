package com.gousade.redis;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-16 9:06:16
 * @description 
 */
@Slf4j
public class JedisTestTransactional {
	
	@Test
	public void test() {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		Transaction transaction = jedis.multi();
		transaction.set("k17", "v15");
		transaction.set("k18", "v16");
//		transaction.exec();
		transaction.discard();
		jedis.close();
	}
	
	public boolean transMethod() throws InterruptedException {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		int balance;// 可用余额
		int debt;// 欠额
		int amtToSubtract = 10;// 实刷额度
		jedis.watch("balance");
		// jedis.set("balance","5");//此句不该出现，讲课方便。模拟其他程序已经修改了该条目
		Thread.sleep(7000);
		balance = Integer.parseInt(jedis.get("balance"));
		if (balance < amtToSubtract) {
			jedis.unwatch();
			System.out.println("the balance modified by anthor thread!");
			jedis.close();
			return false;
		} else {
			System.out.println("***********transaction");
			Transaction transaction = jedis.multi();
			transaction.decrBy("balance", amtToSubtract);
			transaction.incrBy("debt", amtToSubtract);
			transaction.exec();
			balance = Integer.parseInt(jedis.get("balance"));
			debt = Integer.parseInt(jedis.get("debt"));

			System.out.println("*******" + balance);
			System.out.println("*******" + debt);
			jedis.close();
			return true;
		}
	}
	
	@Test
	public void testTransMethod() throws InterruptedException {
		boolean result = transMethod();
		log.info("result:{}",result);
	}

}
