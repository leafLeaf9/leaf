package com.gousade.log;

import com.gousade.shiro.ShiroUtils;
import com.mzt.logapi.beans.Operator;
import com.mzt.logapi.service.IOperatorGetService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogRecordConfiguration {
    @Bean
    public IOperatorGetService operatorGetService() {
        return () -> ShiroUtils.getShiroSessionUser() == null ?
                new Operator("guest") : new Operator(ShiroUtils.getShiroSessionUser().getUserId());
    }

    /*@Bean
    public ILogRecordService recordService() {
        return new DefaultLogRecordServiceImpl();
    }*/

}
