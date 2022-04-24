package com.gousade.scheduler;

import com.gousade.redis.RedisUtils;
import com.gousade.service.GoCqHttpRoBotService;
import com.gousade.service.MiHoYoService;
import com.gousade.service.impl.MiHoYoServiceImpl;
import com.gousade.util.Java8DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * @author woxigousade
 * @date 2019/12/18
 * 0 00 12 * * ? 每天中午十二点触发
 * 0 15 10 ? * * 每天早上10：15触发
 * 0 15 10 * * ? 每天早上10：15触发
 * 0 15 10 * * ? * 每天早上10：15触发
 * 0 15 10 * * ? 2005 2005年的每天早上10：15触发
 * 0 * 14 * * ? 每天从下午2点开始到2点59分每分钟一次触发
 * 0 0/5 14 * * ? 每天从下午2点开始到2：55分结束每5分钟一次触发
 * 0 0/5 14,18 * * ? 每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
 * 0 0-5 14 * * ? 每天14:00至14:05每分钟一次触发
 * 0 10,44 14 ? 3 WED 三月的每周三的14：10和14：44触发
 * 0 15 10 ? * MON-FRI 每个周一、周二、周三、周四、周五的10：15触发
 * cron表达式的星期中1为周日SUN 2为周一MON以此类推 7为周六SAT，但是此处使用org.springframework.scheduling.support.CronSequenceGenerator类，
 * 0,1,2,3,4,5,6分别表示SUN,MON,TUE,WED,THU,FRI,SAT所以周一到周五应该是1,2,3,4,5或者MON,TUE,WED,THU,FRI
 * Sunday can be represented as 0 or 7 0和7都可以代表周日
 * http://www.manongjc.com/detail/15-zlvwmipbrrbfjht.html
 */
@Component
@EnableScheduling
@EnableAsync
@Slf4j
public class SchedulerTask {

    @Resource
    private RedisUtils redisUtils;

    @Autowired
    private GoCqHttpRoBotService roBotService;

    @Autowired
    private MiHoYoService miHoYoService;

    @Async
    @Scheduled(cron = "0 0/5 * * * ?")
    @Scheduled(cron = "0 0/9 * * * ?")
    public void logCurrentTimeScheduled() {
        String currentTime = ZonedDateTime
                .now(/*Java8DateUtil.CTT*/)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS ZZZZ E 'CST'"));
        log.info("Current Time : {}", currentTime);
    }

    @Async
    @Scheduled(cron = "00 00 08 * * ?")
    @Scheduled(cron = "00 00 22 * * ?")
    public void remindMiHoYoSignInSpecifiedGroup() {
        String groups = String.valueOf(redisUtils.get("goCqHttpRobot:robotRemindGroups"));
        int hour = ZonedDateTime.now().getHour();
        String message = String.format("已经%s点了，记得去米游社签到。", hour);
        if (hour == 22) {
            message = String.format("已经%s点了，查看一下有没有忘记米游社签到。", hour);
        }
        for (String group : groups.split(",")) {
            roBotService.sendGroupMsg(group, message);
        }
    }

    @Async
    @Scheduled(cron = "00 30 07 * * ?")
    public void miHoYoAutoSignInSpecifiedGroup() {
        ZonedDateTime now = ZonedDateTime.now();
        String group = String.valueOf(redisUtils.get("goCqHttpRobot:robotPushGroup"));
        String signStartMessage = String.format("现在是%s, 开始执行米游社自动签到。", Java8DateUtil.formatZonedDateTime(now));
        roBotService.sendGroupMsg(group, signStartMessage);
        Set<String> set = redisUtils.keys(MiHoYoServiceImpl.MI_HO_YO_COOKIE_KEY_PREFIX + "*");
        set.stream().sorted().forEach(key -> {
            String userId = key.substring(key.lastIndexOf(":") + 1);
            roBotService.miHoYoSign(userId, group);
        });
        String signEndMessage = "执行米游社自动签到结束, 请收到登录失效通知的重新绑定cookie。在群里发送ck教程即可获取绑定教程。";
        roBotService.sendGroupMsg(group, signEndMessage);
    }
}
