package com.gousade.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: woxigousade <woxigsd@gmail.com>
 * @date: 2020/9/1/0001 20:17
 * @description: spring boot general config bean
 */
@Configuration
public class GousadeApplicationConfig {

    /**
     * 此方法使得@JSONField(format = "yyyy-MM-dd HH:mm:ss")注解可以正确地给前端返回日期格式数据，而不是时间戳
     * 无此方法时需要使用Jackson的@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")，不能使用fastjson
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fasHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fasHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fasHttpMessageConverter);

    }

    //Tomcat large file upload connection reset
    @Bean
    public TomcatServletWebServerFactory tomcatEmbedded() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                //-1 means unlimited tomcatEmbedded 这段代码是为了解决，上传文件大于10M出现连接重置的问题。此异常内容 GlobalException 也捕获不到。
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });
        return tomcat;
    }
}
