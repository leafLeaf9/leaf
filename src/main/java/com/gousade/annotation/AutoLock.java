package com.gousade.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 锁的基本信息
 */
@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoLock {
    /**
     * 锁前缀
     */
    String prefix() default "leaf:lock";

    /**
     * 加锁时间
     */
    long lockTime() default 30;

    /**
     * 是否尝试加锁
     */
    boolean tryLock() default true;

    /**
     * 等待时间，-1 不等待
     */
    long waitTime() default -1;

    /**
     * 锁时间类型
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
