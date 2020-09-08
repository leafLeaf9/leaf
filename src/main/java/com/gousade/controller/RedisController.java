package com.gousade.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gousade.pojo.User;
import com.gousade.redis.RedisUtil;
import com.gousade.utils.SaltUtil;

import lombok.extern.slf4j.Slf4j;

/**
* @author woxigsd@gmail.com
* @date 2020-8-12 15:21:33
* 
*/
@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisController {

    private static int ExpireTime = 600;   // redis中存储的过期时间60s

    @Resource
    private RedisUtil redisUtil;
    
    @RequestMapping("/keys")
    public Object keys(String...patterns){
    	return redisUtil.keys(patterns);
        
    }

    @RequestMapping("set")
    public boolean redisset(String key){
    	redisUtil.selectDB(5);
        User userEntity =new User();
        userEntity.setId(SaltUtil.generateUUId());
        userEntity.setUserName("reidsuser");
        userEntity.setCreateTime(new Date());
        //return redisUtil.set(key,userEntity,ExpireTime);

        return redisUtil.set(key,userEntity);
    }

    @RequestMapping("get")
    public Object redisget(String key){
//    	Object result= redisUtil.get("redis@Cacheable::public abstract java.util.List com.gousade.mapper.UserMapper.selectUserList({\"orderStr\":\"order by null null\",\"userName\":\"\"})");
//    	return result;
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    public boolean expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }
    
    @RequestMapping("getExpire")
    public long getExpire(String key){
        return redisUtil.getExpire(key);
    }
    
    @RequestMapping("persist")
    public boolean persist(String key){
        return redisUtil.persist(key);
    }
    
    @RequestMapping("/getUser")
    @Cacheable(value="redis@Cacheable-config6")
    public User getUser() {
        User user=new User("aa@126.com", "aa", "aa123456", "123");
        log.info("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }
}