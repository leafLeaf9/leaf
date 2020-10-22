package com.gousade.controller;

import com.gousade.commonutils.ResponseResult;
import com.gousade.pojo.User;
import com.gousade.redis.RedisUtil;
import com.gousade.utils.SaltUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author woxigsd@gmail.com
 * @date 2020-8-12 15:21:33
 */
@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisController {

	private static final int ExpireTime = 600; // redis中存储的过期时间60s

	@Resource
	private RedisUtil redisUtil;

	@Autowired
	@Qualifier("enableTransactionRedisTemplate")
	private RedisTemplate<String, Object> redisTemplate;

	@RequestMapping(value = "/keys", method = RequestMethod.POST)
	public Object keys(String... patterns) {
		return redisUtil.keys(patterns);
	}

	@RequestMapping(value = "set", method = RequestMethod.POST)
	public boolean redisset(String key) {
		redisUtil.selectDB(5);
		User userEntity = new User();
		userEntity.setId(SaltUtil.generateUUId());
		userEntity.setUserName("reidsuser");
		userEntity.setCreateTime(new Date());
		// return redisUtil.set(key,userEntity,ExpireTime);
		return redisUtil.set(key, key, 1800);
	}

	@RequestMapping(value = "get", method = RequestMethod.POST)
	public Object redisget(String key) {
		return redisUtil.get(key);
	}

	@RequestMapping(value = "expire", method = RequestMethod.POST)
	public boolean expire(String key) {
		return redisUtil.expire(key, ExpireTime);
	}

	@RequestMapping(value = "getExpire", method = RequestMethod.POST)
	public long getExpire(String key) {
		return redisUtil.getExpire(key);
	}

	@RequestMapping(value = "persist", method = RequestMethod.POST)
	public boolean persist(String key) {
		return redisUtil.persist(key);
	}

	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	@Cacheable(value = "redis@Cacheable-config6")
	public User getUser() {
		User user = User.builder().userId("sss").userName("name").build();
		log.info("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
		return user;
	}

	/**
	 * @return 在事务中(multi - exec)使用redisUtil.incr()会报空指针异常，原因不明，使用开启事务的enableTransactionRedisTemplate来操作却不会异常
	 */
	@RequestMapping(value = "increment", method = RequestMethod.POST)
	public ResponseResult increment() {
		redisTemplate.multi();
//        redisUtil.incr("increment", 1);
		redisTemplate.opsForValue().increment("increment", 1);
		redisTemplate.exec();
		return ResponseResult.renderSuccess().message("自增成功").data("result", redisUtil.get("increment"));
	}

	/**
	 * @return 由于 enableTransactionSupport 属性的默认值是 false，导致了每一个 RedisConnection
	 * 都是重新获取的。
	 * @description 开启事务后也许会导致空指针异常的问题
	 */
	@RequestMapping(value = "/multi", method = RequestMethod.POST)
	public ResponseResult multi(String key) {
		redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.multi();
		redisUtil.set("testMulti1", key, 1800L);
		redisUtil.set("testMulti2", Integer.parseInt(key), 1800L);
		redisTemplate.opsForValue().increment("testMulti2", 2);
//    	redisUtil.lGet("testMulti1", 0, -1);
//    	redisTemplate.discard();
		List<Object> list = redisTemplate.exec();
		return ResponseResult.renderSuccess().message("事务成功").data("result", list);
	}

	/**
	 * @return 可以通过使用 SessionCallback，该接口保证其内部所有操作都是在同一个Session中。
	 */
	@RequestMapping(value = "/testSessionCallback", method = RequestMethod.POST)
	public ResponseResult testSessionCallback(String key) {
		SessionCallback<Object> callback = new SessionCallback<Object>() {
			@SuppressWarnings({"unchecked", "rawtypes"})
			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				operations.multi();
				operations.opsForValue().set("testSessionCallBackMulti1", key, 1800L, TimeUnit.SECONDS);
				operations.opsForValue().set("testSessionCallBackMulti2", key, 1800L, TimeUnit.SECONDS);
				operations.opsForValue().set("testSessionCallBackMulti3", key, 1800L, TimeUnit.SECONDS);
				return operations.exec();
			}
		};
		return ResponseResult.renderSuccess().message("事务成功").data("result", redisTemplate.execute(callback));
	}

	public SessionCallback<Object> createSessionCallback(Map<String, Object> map) {
		return new SessionCallback<Object>() {
			@SuppressWarnings({"unchecked", "rawtypes"})
			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				operations.multi();
				map.forEach((k, v) -> operations.opsForValue().set(k, v, 1800L, TimeUnit.SECONDS));
				return operations.exec();
			}
		};
	}

	/**
	 * @throws InterruptedException
	 * @description 借款场景使用watch监控命令实现乐观锁机制
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/lockedLoanByWatch", method = RequestMethod.POST)
	public ResponseResult lockedLoanByWatch() throws InterruptedException {
		redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.multi();
		redisUtil.set("balance", 100, 1800L);
		redisUtil.set("debt", 0, 1800L);
		redisTemplate.exec();
		redisTemplate.watch("balance");
		log.info("now balance is : {}", redisUtil.get("balance"));
		log.info("now debt is : {}", redisUtil.get("debt"));
		int originBalance = Integer.parseInt(redisUtil.get("balance").toString());
		int originDebt = Integer.parseInt(redisUtil.get("debt").toString());
		int balance;// 可用余额
		int debt;// 欠额
		int amtToSubtract = 10;// 实刷额度
		new Thread(() -> {
			redisUtil.set("balance", 5, 1800L);
			log.warn("另一个线程修改balance为5");
			redisUtil.set("balance", 60, 1800L);
			log.warn("另一个线程修改balance为60");
			redisUtil.set("balance", 100, 1800L);
			log.warn("另一个线程修改balance为100");
		})
		// 线程开启时修改了balance会导致借款失败，关闭时可成功借款。
//		.start()
		;
		Thread.sleep(2500);
		balance = Integer.parseInt(redisUtil.get("balance").toString());
		if (balance < amtToSubtract) {
			redisTemplate.unwatch();
			log.info("the balance modified by anthor thread!借款失败。");
			return ResponseResult.renderError().message("the balance modified by anthor thread!借款失败。");
		} else {
			log.info("transaction start");
			redisTemplate.multi();
			redisTemplate.opsForValue().decrement("balance", amtToSubtract);
			redisTemplate.opsForValue().increment("debt", amtToSubtract);
//			redisUtil.decr("balance", amtToSubtract);
//			redisUtil.incr("debt", amtToSubtract);
			redisTemplate.exec();
			log.info("now balance is : {}", redisUtil.get("balance"));
			log.info("now debt is : {}", redisUtil.get("debt"));
			int currentBalance = Integer.parseInt(redisUtil.get("balance").toString());
			int currentDebt = Integer.parseInt(redisUtil.get("debt").toString());
			if (currentBalance == originBalance - amtToSubtract && currentDebt == originDebt + amtToSubtract) {
				log.info("借款成功");
				return ResponseResult.renderSuccess().message("借款成功");
			} else {
				log.error("借款失败");
				return ResponseResult.renderError().message("借款失败");
			}
		}
	}
}