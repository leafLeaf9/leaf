package com.gousade.scheduler.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Autowired
    private CustomJobFactory customJobFactory;

    /**
     * 初始注入scheduler
     */
    @Bean
    public Scheduler scheduler() throws SchedulerException {
        SchedulerFactory schedulerFactoryBean = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        // 自定义 JobFactory 使得在 Quartz Job 中可以使用 @Autowired
        scheduler.setJobFactory(customJobFactory);
        return scheduler;
    }
}
