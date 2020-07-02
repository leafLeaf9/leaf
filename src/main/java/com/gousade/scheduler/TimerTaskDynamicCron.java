package com.gousade.scheduler;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 动态配置定时任务
 */
@Slf4j
@Component
public class TimerTaskDynamicCron {
	
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    
    private List<ScheduledFuture<?>> robotFutureList=new CopyOnWriteArrayList<ScheduledFuture<?>>();
    
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }
    
    /**
     * 项目启动时就运行的
     */
    @PostConstruct
    public void init() throws Exception {
    		startRobotTask();
	}
    
    /**
     * 开启定时任务
     * @param task
     */
    public void startRobotTask() {
    	ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(new Runnable() {
			@Override
			public void run() {
				log.warn("Task...........run..{}");
				//获取最近的batchId，看里面还有多少数据可以去获取
			}
		},new Trigger(){
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext){
            	//0/30 * * * * ?
            	CronTrigger trigger = new CronTrigger("0 0/3 * * * *");
                Date nextExec = trigger.nextExecutionTime(triggerContext);
                return nextExec;
            }
        });
    	robotFutureList.add(future);
    }
    
    
    /**
     * 停止定时任务
     */
    public void stopRobotTask() {
        for(ScheduledFuture<?> future:robotFutureList){
        	if (future != null) {
        		/**
        		 * 不会马上停止任务,会等任务执行完
        		 */
                future.cancel(true);
            }
        }
        robotFutureList.clear();
        log.error("DynamicTask.stopTask()");
    }

}