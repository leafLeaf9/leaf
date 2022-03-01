package com.gousade.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 方法使用切面计时的注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Timing {
    /**
     * 用时显示单位
     * 可选项为MILLISECONDS、SECONDS、MINUTES，其它单位没有必要。
     */
    TimeUnit unit() default TimeUnit.MILLISECONDS;
}
