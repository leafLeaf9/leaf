package com.gousade.controller;

import com.aliyuncs.exceptions.ClientException;
import com.gousade.common.ResponseResult;
import com.gousade.pojo.User;
import com.gousade.redis.RedisSmsCodeUtil;
import com.gousade.redis.RedisUtils;
import com.gousade.service.EasyExcelDataService;
import com.gousade.service.SmsResponseLogService;
import com.gousade.service.UserService;
import com.gousade.shiro.ShiroUtils;
import com.gousade.util.SaltUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author woxigsd@gmail.com
 * @date 2020-8-14 11:05:31
 */
@Slf4j
@RestController
@RequestMapping(value = "/admin/smsCode")
public class SmsCodeController {

    @Autowired
    private RedisSmsCodeUtil smsCodeUtil;

    @Resource
    private RedisUtils redisUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private SmsResponseLogService smsResponseLogService;

    @Autowired
    private EasyExcelDataService easyExcelDataService;

    @GetMapping("/test")
    public ResponseResult test() throws InterruptedException {
        log.info("当前线程是：" + Thread.currentThread().getName());
        Thread.sleep(30000);
        return ResponseResult.renderSuccess();
    }

    @RequestMapping(value = "/sendSmsCode", method = RequestMethod.POST)
    public Object sendSmsCode(String phoneNumber) throws ClientException {
        return smsCodeUtil.sendSmsCode(phoneNumber);
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public Object validate(String checkCode, User user) {
        Object redisGetSentCode = redisUtils.get(user.getPhoneNumber());
        if (redisGetSentCode == null) {
            return ResponseResult.renderError().message("验证码已失效，请重新获取。");
        } else {
            if (redisGetSentCode.toString().equals(checkCode)) {
                user.setId(ShiroUtils.getShiroSessionUser() != null ? ShiroUtils.getShiroSessionUser().getId()
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
