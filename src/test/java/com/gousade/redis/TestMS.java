package com.gousade.redis;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-16 15:18:36
 * @description 
 */
@Slf4j
public class TestMS {
	
	@SuppressWarnings("resource")
	@Test
	public void testMasterAndSlave() {
		Jedis jedis_M = new Jedis("127.0.0.1", 6379);
		Jedis jedis_S = new Jedis("127.0.0.1", 6380);

		jedis_S.slaveof("127.0.0.1", 6379);

		jedis_M.set("class", "slave of 1122V2");

		String result = jedis_S.get("class");//可能有延迟，需再次启动才能使用
		log.info(result);
	}

}
