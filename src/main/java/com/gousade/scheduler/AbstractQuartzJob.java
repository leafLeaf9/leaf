package com.gousade.scheduler;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.gousade.constant.ScheduleConstants;
import com.gousade.entity.SysQuartzJob;
import com.gousade.entity.SysQuartzJobLog;
import com.gousade.service.ISysQuartzJobLogService;
import com.gousade.util.SpringBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
public abstract class AbstractQuartzJob implements Job {
    /**
     * 线程本地变量
     */
    private static final ThreadLocal<LocalDateTime> threadLocal = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SysQuartzJob sysJob = new SysQuartzJob();
        BeanUtils.copyProperties(context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES), sysJob);
        try {
            before(context, sysJob);
            doExecute(context, sysJob);
            after(context, sysJob, null);
        } catch (Exception e) {
            log.error("任务执行异常  - ：", e);
            after(context, sysJob, e);
        }
    }

    /**
     * 执行前
     *
     * @param context 工作执行上下文对象
     * @param sysJob  系统计划任务
     */
    protected void before(JobExecutionContext context, SysQuartzJob sysJob) {
        threadLocal.set(LocalDateTime.now());
    }

    /**
     * 执行后
     *
     * @param context 工作执行上下文对象
     * @param sysJob  系统计划任务
     */
    protected void after(JobExecutionContext context, SysQuartzJob sysJob, Exception e) {
        LocalDateTime startTime = threadLocal.get();
        threadLocal.remove();

        final SysQuartzJobLog sysJobLog = new SysQuartzJobLog();
        sysJobLog.setJobName(sysJob.getJobName());
        sysJobLog.setJobGroup(sysJob.getJobGroup());
        sysJobLog.setInvokeTarget(sysJob.getInvokeTarget());
        sysJobLog.setStartTime(startTime);
        sysJobLog.setEndTime(LocalDateTime.now());
        long runMs = ChronoUnit.MILLIS.between(sysJobLog.getStartTime(), sysJobLog.getEndTime());
        sysJobLog.setJobMessage(sysJobLog.getJobName() + " 总共耗时：" + runMs + "毫秒");
        if (e != null) {
            sysJobLog.setStatus(ScheduleConstants.FAIL_STATUS);
            String errorMsg = StringUtils.substring(ExceptionUtil.getMessage(e), 0, 2000);
            sysJobLog.setExceptionInfo(errorMsg);
        } else {
            sysJobLog.setStatus(ScheduleConstants.SUCCESS_STATUS);
        }
        //  这里获取service然后插入库中
        SpringBeanUtils.getBean(ISysQuartzJobLogService.class).save(sysJobLog);
    }

    /**
     * 子类去实现
     */
    protected abstract void doExecute(JobExecutionContext jobExecutionContext, SysQuartzJob sysJob) throws Exception;
}
