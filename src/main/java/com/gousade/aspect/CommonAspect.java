package com.gousade.aspect;


import com.gousade.annotation.Retryable;
import com.gousade.annotation.Timing;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Aspect
@Slf4j
public class CommonAspect {

    @Pointcut("@annotation(com.gousade.annotation.Retryable)")
    public void retryPointcut() {
    }

    @Pointcut("@annotation(com.gousade.annotation.Timing)")
    public void timingPointcut() {
    }

    @Around("retryPointcut() && @annotation(retry)")
    public Object redisRetryAround(ProceedingJoinPoint point, Retryable retry) throws Throwable {
        Exception lastException = new RuntimeException("重试失败");
        for (int i = 1; i <= retry.max(); i++) {
            try {
                return point.proceed();
            } catch (Exception e) {
                lastException = e;
                TimeUnit.MILLISECONDS.sleep(retry.delayMills());
                if (log.isDebugEnabled()) {
                    log.debug(point.getSignature() + "第" + i + "次重试。");
                }
            }
        }
        throw lastException;
    }

    @Around("timingPointcut() && @annotation(timing)")
    public Object timingAround(ProceedingJoinPoint point, Timing timing) throws Throwable {
        long start = System.currentTimeMillis();
        Throwable throwable = null;
        Object result = null;

        try {
            result = point.proceed();
        } catch (Throwable e) {
            throwable = e;
        }

        long usage = System.currentTimeMillis() - start;
        String methodName = point.getSignature().getName();
        String template = "调用[" + methodName + "] 用时 %d %s.";
        TimeUnit unit = TimeUnit.MILLISECONDS;

        switch (timing.unit()) {
            case SECONDS:
                log.info(String.format(template, unit.toSeconds(usage), "sec"));
                break;
            case MINUTES:
                log.info(String.format(template, unit.toMinutes(usage), "min"));
            default:
                log.info(String.format(template, usage, "ms"));
        }
        if (throwable != null) {
            throw throwable;
        }
        return result;
    }
}
