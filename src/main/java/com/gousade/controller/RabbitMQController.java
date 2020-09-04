package com.gousade.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gousade.commonutils.ResponseResult;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-4 14:24:23
 * @description 
 */
@Api(tags="RabbitMQ")
@Slf4j
@RestController
@RequestMapping(value = "/rabbitmq")
public class RabbitMQController {
	
	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private AmqpAdmin amqpAdmin;
	
	@RequestMapping(value="/contrextloads",method=RequestMethod.GET)
	public ResponseResult contrextloads() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("msg","这是第一个消息");
		map.put("data", Arrays.asList("helloworld",123,true));
		rabbitTemplate.convertAndSend("exchange.direct","gousade.news",ResponseResult.renderSuccess().message("发送点对点消息"));
		rabbitTemplate.convertAndSend("exchange.direct","gousade.news","aa");
		rabbitTemplate.convertAndSend("exchange.direct","gousade.news",map);
		rabbitTemplate.convertAndSend("exchange.fanout","",ResponseResult.renderError().message("调用exchange.fanout发送广播消息"));
		log.info("发送消息成功。");
		return ResponseResult.renderSuccess().message("发送消息成功。");
	}
	
	@RequestMapping(value="/createExchange",method=RequestMethod.GET)
	public ResponseResult createExchange() {
		amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));
		log.info("创建exchanges成功");
		amqpAdmin.declareQueue(new Queue("amqpAdmin.queue"));
		amqpAdmin.declareBinding(new Binding("amqpAdmin.queue", Binding.DestinationType.QUEUE,"amqpAdmin.exchange","amqpAdmin.queue",null));
		return ResponseResult.renderSuccess().message("创建exchanges成功");
	}

}
