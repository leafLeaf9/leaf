package com.gousade.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LockField {
    String[] fieldNames() default {};
}
