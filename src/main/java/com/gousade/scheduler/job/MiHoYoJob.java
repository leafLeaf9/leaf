package com.gousade.scheduler.job;

import com.gousade.redis.RedisUtils;
import com.gousade.service.GoCqHttpRoBotService;
import com.gousade.service.impl.MiHoYoServiceImpl;
import com.gousade.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.ZonedDateTime;
import java.util.Set;

@Component
@Slf4j
public class MiHoYoJob {
    @Resource
    private RedisUtils redisUtils;

    @Autowired
    private GoCqHttpRoBotService roBotService;

    public void miHoYoAutoSignInSpecifiedGroup() {
        ZonedDateTime now = ZonedDateTime.now();
        String group = String.valueOf(redisUtils.get("goCqHttpRobot:robotPushGroup"));
        String signStartMessage = String.format("现在是%s, 开始执行米游社自动签到。", DateUtils.formatTime(now));
        roBotService.sendGroupMsg(group, signStartMessage);
        Set<String> set = redisUtils.keys(MiHoYoServiceImpl.MI_HO_YO_COOKIE_KEY_PREFIX + "*");
        set.stream().sorted().forEach(key -> {
            String userId = key.substring(key.lastIndexOf(":") + 1);
            try {
                roBotService.miHoYoSign(userId, group);
            } catch (Exception e) {
                log.error(userId + "米游社自动签到时发生异常。", e);
            }
        });
        String signEndMessage = "执行米游社自动签到结束, 请收到登录失效通知的重新绑定cookie。在群里发送ck教程即可获取绑定教程。";
        roBotService.sendGroupMsg(group, signEndMessage);
    }
}
