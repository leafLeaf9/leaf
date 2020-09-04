package com.gousade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gousade.commonutils.ResponseResult;
import com.gousade.mail.MailManage;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-4 16:36:49
 * @description 
 */
@Api(tags="Mail")
@Slf4j
@RestController
@RequestMapping(value = "/mail")
public class MailController {
	
	@Autowired
    private MailManage mailManage;
	
	@RequestMapping(value="/mailSend",method=RequestMethod.GET)
	public ResponseResult mailSend() {
		mailManage.sendSimpleMail("1207366201@qq.com","test simple mail"," hello this is simple mail");
		log.info("发送消息成功。");
		return ResponseResult.renderSuccess().message("发送消息成功。");
	}

}
