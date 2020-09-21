package com.gousade.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-7 15:14:30
 * @description 使thymeleaf和jsp共存
 */
@Configuration
@EnableWebMvc
public class WebViewResolverConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewNames("*");
        resolver.setOrder(2);
        return resolver;
    }

	/*@Bean
	public ITemplateResolver templateResolver() {
	    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	    templateResolver.setTemplateMode("HTML");
	    templateResolver.setPrefix("/WEB-INF/views/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setCharacterEncoding("utf-8");
	    templateResolver.setCacheable(false);
	    return templateResolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine() {
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver());
	    return templateEngine;
	}
	
	@Bean
	public ThymeleafViewResolver viewResolverThymeLeaf() {
	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(templateEngine());
	    viewResolver.setCharacterEncoding("utf-8");
	    viewResolver.setViewNames(new String[]{"thymeleaf/*"});
	    viewResolver.setOrder(1);
	    return viewResolver;
	}*/

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * Locations of static resources. Defaults to classpath:[/META-INF/resources/,
     * /resources/, /static/, /public/].
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/").addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConverter.setFastJsonConfig(fastJsonConfig);
//		converters.add(fastJsonConverter);//这会让fastJsonConverter排在消息转换器管道列表的最后，可能会轮不到它处理消息转换
        converters.add(0, fastJsonConverter);//要显示指明将fastJsonConverter排在消息转换器管道列表的首位
    }

}
