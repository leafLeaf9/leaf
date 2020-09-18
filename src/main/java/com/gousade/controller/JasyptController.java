package com.gousade.controller;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gousade.commonutils.ResponseResult;
import com.gousade.jasypt.JasyptUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author woxigsd@gmail.com
 * @date 2020-8-21 16:22:57 Description:jasypt加解密
 */
@Slf4j
@RequestMapping("/jasypt")
@RestController
public class JasyptController {

	@Resource
	JasyptUtil jasyptUtil;

	@RequiresRoles(value = { "超级管理员", "jasypt" }, logical = Logical.OR)
	@RequestMapping(value = "/encrypt", method = RequestMethod.POST)
	public Object encrypt(String value) {
		log.info(jasyptUtil.encrypt(value));
		return jasyptUtil.encrypt(value);
	}

	@RequiresRoles({ "超级管理员" })
	@RequestMapping(value = "/decrypt", method = RequestMethod.POST)
	public Object decrypt(String value) {
		log.info(jasyptUtil.decrypt(value));
		return ResponseResult.renderSuccess().message("解密成功").data("value", jasyptUtil.decrypt(value));
	}

	@RequiresRoles({ "超级管理员" })
	@RequestMapping(value = "/encryptWithSalt", method = RequestMethod.POST)
	public Object encryptWithSalt(String salt, String value) {
		log.info(jasyptUtil.encryptWithSalt(salt, value));
		return jasyptUtil.encryptWithSalt(salt, value);
	}

	@RequiresRoles({ "超级管理员" })
	@RequestMapping(value = "/decryptWithSalt", method = RequestMethod.POST)
	public Object decryptWithSalt(String salt, String value) {
		log.info(jasyptUtil.decryptWithSalt(salt, value));
		return jasyptUtil.decryptWithSalt(salt, value);
	}

}
