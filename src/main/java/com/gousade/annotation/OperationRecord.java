package com.gousade.annotation;

import org.springframework.cache.annotation.Cacheable;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationRecord {
    int operationNum() default 0;

    /**
     * Spring Expression Language (SpEL) expression.
     * EL表达式格式, 例如: '用户' + #user.userName + '的地址' 字符串需要单引号，否则EL表达式解析会报错
     *
     * @see Cacheable#key()
     */
    String operationDescription() default "";
}
