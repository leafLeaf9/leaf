package com.gousade.service;

import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.gousade.commonutils.ResponseResult;

import lombok.extern.slf4j.Slf4j;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-4 11:05:47
 * @description 
 */
@Slf4j
@Service
public class RabbitMQService {
	
	@RabbitListener(queues = "gousade.news")
    public void receiveObject(Object responseResult){
		log.info("类型："+responseResult.getClass());
        log.info("收到ResponseResult消息："+responseResult);
    }
	
	/*@RabbitListener(queues = "gousade.news")
	public void receiveResponseResult(ResponseResult responseResult){
		log.info("类型："+responseResult.getClass());
	    log.info("收到ResponseResult消息："+responseResult);
	}
	
	@RabbitListener(queues = "gousade.news")
	public void receiveString(String responseResult){
	   log.info("收到String消息："+responseResult);
	}
	
	@RabbitListener(queues = "gousade.news")
	public void receiveMap(Map<String,Object> map){
	   log.info("收到Map消息："+map);
	}*/

    @RabbitListener(queues = "gousade")
    public void receiveMessage(Message message){
    	log.info(message.getBody().toString());
    	log.info(message.getMessageProperties().toString());
    }

}
