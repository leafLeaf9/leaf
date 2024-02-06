package com.gousade.config;

import com.gousade.aspect.AutoLockAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LockAutoConfig {
    @Bean
    public AutoLockAspect autoLockAspect() {
        return new AutoLockAspect();
    }

    // 如果改为 @AutoConfiguration 则spring.factories 中内容
//    org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.gousade.config.LockAutoConfig
}
