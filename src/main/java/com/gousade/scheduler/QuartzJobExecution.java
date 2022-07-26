package com.gousade.scheduler;


import com.gousade.entity.SysQuartzJob;
import com.gousade.scheduler.util.JobInvokeUtil;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, SysQuartzJob sysJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
