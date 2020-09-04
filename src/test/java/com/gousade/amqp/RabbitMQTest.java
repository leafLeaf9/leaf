package com.gousade.amqp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gousade.commonutils.ResponseResult;

import lombok.extern.slf4j.Slf4j;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-4 9:41:38
 * @description 
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQTest {
	
	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	/**
	 * exchange.direct单播(点对点)
	 */
	@Test
    public void contrextloads() {
		//Message需要自己构造一个;定义消息体内容和消息头
		//rabbitTemplate.send(exchange, routingKey, message);
		//object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("msg","这是第一个消息");
		map.put("data", Arrays.asList("helloworld",123,true));
		rabbitTemplate.convertAndSend("exchange.direct","gousade.news",ResponseResult.renderSuccess().message("发送点对点消息"));
		rabbitTemplate.convertAndSend("exchange.direct","gousade.news","aa");
		rabbitTemplate.convertAndSend("exchange.direct","gousade.news",map);
	}
	
	@Test
	public void receive(){
		Object o = rabbitTemplate.receiveAndConvert("gousade.news");
		log.info(o.getClass().toString());
		log.info(o.toString());
	}
	
	/**
	 * exchange.fanout广播
	 */
	@Test
	public void sendMsg(){
		rabbitTemplate.convertAndSend("exchange.fanout","",ResponseResult.renderError().message("调用exchange.fanout发送广播消息"));
	}

}
