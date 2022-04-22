package com.gousade.redis;

import com.aliyuncs.exceptions.ClientException;
import com.gousade.annotation.OperationRecord;
import com.gousade.common.ResponseResult;
import com.gousade.util.SmsUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author woxigsd@gmail.com
 * @date 2020-8-14 10:07:20 Description:短信验证码工具类
 */
@Component
public class RedisSmsCodeUtil {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private SmsUtil smsUtil;

    @OperationRecord(operationNum = 9999, operationDescription = "短信验证码发送")
    public Object sendSmsCode(String phoneNumber) throws ClientException {
        int randomCode = (int) ((Math.random() * 9 + 1) * 100000);
        Object redisGetSentCode = redisUtils.get(phoneNumber);
        if (redisGetSentCode == null) {
//            redisUtil.set(phoneNumber, randomCode, 180L);
            smsUtil.sendSms(phoneNumber, randomCode);
            return ResponseResult.renderSuccess().message("验证码发送成功。");
        } else {
            return ResponseResult.renderError().message("验证码已存在，到期前请勿重复发送。");
        }
    }

}
