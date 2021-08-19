package com.gousade.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author woxigousade
 * @date 2021/8/19
 */
public class RequestSentinelAspect {
    @Pointcut("execution(* com.gousade..*.controller.*.*(..))")
    public void requestSentinelPointcut() {
    }

    @Before("requestSentinelPointcut()")
    public void requestSentinel(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //rateLimiterService.checkLimiter(method, joinPoint.getArgs().hashCode());
    }
}
