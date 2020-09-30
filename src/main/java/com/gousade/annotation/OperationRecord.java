package com.gousade.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationRecord {
	int operationNum() default 0;

	String operationDescription() default "";
}
