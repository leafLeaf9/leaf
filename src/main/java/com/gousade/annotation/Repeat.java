package com.gousade.annotation;

import java.lang.annotation.*;


@Inherited
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Repeat {

    /**
     * 与当前字段一起构成唯一约束的字段
     */
    String[] uniqueWith() default {};

    // 重复数据的提示信息
    String message();
}
