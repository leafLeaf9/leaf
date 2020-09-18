package com.gousade.controller;

import com.aliyuncs.exceptions.ClientException;
import com.gousade.commonutils.ResponseResult;
import com.gousade.pojo.User;
import com.gousade.redis.RedisSmsCodeUtil;
import com.gousade.redis.RedisUtil;
import com.gousade.service.UserService;
import com.gousade.shiro.ShiroUtil;
import com.gousade.utils.SaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author woxigsd@gmail.com
 * @date 2020-8-14 11:05:31 Description:
 */
@RestController
@RequestMapping(value = "/admin/smsCode")
public class SmsCodeController {

	@Autowired
	private RedisSmsCodeUtil smsCodeUtil;

	@Resource
	private RedisUtil redisUtil;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/sendSmsCode", method = RequestMethod.POST)
	public Object sendSmsCode(String phoneNumber) throws ClientException {
		return smsCodeUtil.sendSmsCode(phoneNumber);
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public Object validate(String checkCode, User user) {
		Object redisGetSentCode = redisUtil.get(user.getPhoneNumber());
		if (redisGetSentCode == null) {
			return ResponseResult.renderError().message("验证码已失效，请重新获取。");
		} else {
			if (redisGetSentCode.toString().equals(checkCode)) {
				user.setId(ShiroUtil.getShiroSessionUser() != null ? ShiroUtil.getShiroSessionUser().getId()
						: user.getId());
				user.setSalt(SaltUtil.getUUId());
				user.setPassword(SaltUtil.toHex(user.getPassword(), user.getSalt()));
				return userService.updateOwnPasswordById(user) ? ResponseResult.renderSuccess().message("重置密码成功。")
						: ResponseResult.renderError().message("重置密码失败。");
			} else {
				return ResponseResult.renderError().message("验证码错误，请重新输入。");
			}
		}
	}

}
