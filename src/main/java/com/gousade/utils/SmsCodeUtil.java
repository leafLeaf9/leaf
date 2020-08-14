package com.gousade.utils;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.aliyuncs.exceptions.ClientException;
import com.gousade.controller.common.BaseController;
import com.gousade.redis.RedisUtil;
import com.gousade.service.SmsDemo;

/**
* @author woxigsd@gmail.com
* @date 2020-8-14 10:07:20
* Description:短信验证码工具类
*/
@Component
public class SmsCodeUtil extends BaseController{
	
	@Resource
    private RedisUtil redisUtil;
	
	public Object sendSmsCode(String phoneNumber) throws ClientException {
		int randomCode = (int)((Math.random()*9+1)*100000);
		Object redisGetSentCode =redisUtil.get(phoneNumber);
		if(redisGetSentCode == null) {
			redisUtil.set(phoneNumber,randomCode,180L);
			SmsDemo.sendSms(phoneNumber, randomCode);
			return renderSuccess("验证码发送成功。");
		}else {
			return renderError("验证码已存在，到期前请勿重复发送。");
		}
	}
	
}
