package com.gousade.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author woxigousade
 * @date 2021/6/1
 */

@Component
public class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 通过class获取Bean
     *
     * @param clazz class
     * @param <T>   泛型
     * @return bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtil.applicationContext = applicationContext;
    }
}
