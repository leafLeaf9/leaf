package com.gousade.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gousade.jasypt.JasyptUtil;

import lombok.extern.slf4j.Slf4j;

/**
* @author woxigsd@gmail.com
* @date 2020-8-21 16:22:57
* Description:jasypt加解密测试
*/
@Slf4j
@RequestMapping("/jasypt")
@RestController
public class JasyptController {
	
	@Resource
	JasyptUtil jasyptUtil;
	
	@RequestMapping("/encypt")
    public Object encypt(String value){
		log.info(jasyptUtil.encypt(value));
    	return jasyptUtil.encypt(value);
    }
	
	@RequestMapping("/decypt")
    public Object decypt(String value){
    	return jasyptUtil.decypt(value);
    }

}
