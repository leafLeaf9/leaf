package com.gousade.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledFuture;

/**
 * 动态配置定时任务
 */
@Slf4j
@Component
public class TimerTaskDynamicCron {

	private final List<ScheduledFuture<?>> robotFutureList = new CopyOnWriteArrayList<ScheduledFuture<?>>();
	@Autowired
	private ThreadPoolTaskScheduler threadPoolTaskScheduler;

	@Bean
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		return new ThreadPoolTaskScheduler();
	}

	/**
	 * 项目启动时就运行的
	 */
	@PostConstruct
	public void init() throws Exception {
		startSchedulerTask();
	}

	/**
	 * 开启定时任务
	 */
	public void startSchedulerTask() {
		ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(() -> log.warn("ThreadPoolTaskScheduler run."), triggerContext -> {
			CronTrigger trigger = new CronTrigger("0 0/3 * * * *");
			Date nextExec = trigger.nextExecutionTime(triggerContext);
			return nextExec;
		});
		robotFutureList.add(future);
	}

	/**
	 * 停止定时任务
	 */
	public void stopSchedulerTask() {
		for (ScheduledFuture<?> future : robotFutureList) {
			if (future != null) {
				/**
				 * 不会马上停止任务,会等任务执行完
				 */
				future.cancel(true);
			}
		}
		robotFutureList.clear();
		log.error("ThreadPoolTaskScheduler stop.");
	}

}