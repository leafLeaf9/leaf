package com.gousade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
//import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@SpringBootApplication
@MapperScan("com.*.mapper")

public class StarterManage extends SpringBootServletInitializer{
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(StarterManage.class);
    }
	
	
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

/*	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	    FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
    }*/
	
	public static void main(String[] args) {
//		SpringApplication.run(StarterManage.class, args);
		SpringApplication app = new SpringApplication(StarterManage.class);
	    app.setBannerMode(Banner.Mode.LOG);//使得自定义横幅可以输出到日志文件中,横幅内容在resources/banner.txt中控制
	    app.run(args);
	}
}
