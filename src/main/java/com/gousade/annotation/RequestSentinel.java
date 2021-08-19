package com.gousade.annotation;

import com.gousade.entity.RequestSentinelTypeEnum;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author woxigousade
 * @date 2021/8/19
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestSentinel {
    /**
     * 超时时间内最大请求数，如果小于等于0，则不限制
     */
    long max() default 500;

    /**
     * 超时时长，默认5秒
     */
    long timeout() default 5;

    /**
     * 超时时间单位，默认 秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 限流类型，可组合
     */
    RequestSentinelTypeEnum[] limitType() default {RequestSentinelTypeEnum.USER, RequestSentinelTypeEnum.METHOD};
}
