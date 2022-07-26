package com.gousade.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledFuture;

/**
 * 动态配置定时任务
 *
 * @author woxigousade
 * @date 2019/12/18
 */
@Slf4j
@Component
public class TimerTaskDynamicCron {

    private final List<ScheduledFuture<?>> futureList = new CopyOnWriteArrayList<>();

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     * 项目启动时就运行
     */
    @PostConstruct
    public void init() throws Exception {
        List<String> cronList = new ArrayList<>();
        cronList.add("0 0/3 * * * ?");
        cronList.add("0 0 0 * * ?");
        cronList.forEach(this::startSchedulerTask);
    }

    /**
     * 开启定时任务
     *
     * @param cron 定时表达式
     */
    public void startSchedulerTask(String cron) {
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(() -> log.warn("ThreadPoolTaskScheduler run."),
                triggerContext -> new CronTrigger(cron).nextExecutionTime(triggerContext));
        futureList.add(future);
    }

    /**
     * 停止定时任务
     */
    public void stopSchedulerTask() {
        //不会马上停止任务,会等任务执行完
        futureList.stream().filter(Objects::nonNull).forEach(future -> future.cancel(false));
        futureList.clear();
        log.error("ThreadPoolTaskScheduler stop.");
    }

}