package com.gousade.controller;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequiresRoles(value = {"超级管理员","jasypt"},logical= Logical.OR)
	@RequestMapping(value = "/encypt", method=RequestMethod.POST)
    public Object encypt(String value){
		log.info(jasyptUtil.encypt(value));
    	return jasyptUtil.encypt(value);
    }
	
	@RequiresRoles({"超级管理员"})
	@RequestMapping(value = "/decypt", method=RequestMethod.POST)
    public Object decypt(String value){
    	return jasyptUtil.decypt(value);
    }
	
	@RequiresRoles({"超级管理员"})
	@RequestMapping(value = "/encyptwithSalt", method=RequestMethod.POST)
	public Object encyptwithSalt(String salt, String value){
		log.info(jasyptUtil.encyptwithSalt(salt, value));
    	return jasyptUtil.encyptwithSalt(salt, value);
    }
	
	@RequiresRoles({"超级管理员"})
	@RequestMapping(value = "/decyptwithSalt", method=RequestMethod.POST)
	public Object decyptwithSalt(String salt, String value){
		log.info(jasyptUtil.decyptwithSalt(salt, value));
    	return jasyptUtil.decyptwithSalt(salt, value);
    }
	
}
