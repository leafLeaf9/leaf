package com.gousade.scheduler.job;

import com.gousade.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
public class MySampleJob extends QuartzJobBean {
    private UserService userService;
    private String name;

    // fields ...

    // Inject "MyService" bean
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // Inject the "name" job data property
    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // 获取参数
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        // 业务逻辑 ...
        log.info("------springboot quart job执行" + jobDataMap.get("name").toString() + "#" + context.getTrigger());
    }

}
