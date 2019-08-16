package cn.edu.zstu.manage.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
 
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
 
	//@Autowired
	//LogInterceptor logInterceptor;
 
	@Autowired
	LoginInterceptor loginInterceptor;
 
	/**
	 * 不需要登录拦截的url:登录注册和验证码
	 */
	final String[] notLoginInterceptPaths = {};//"/", "/login/**", "/person/**", "/register/**", "/validcode", "/captchaCheck", "/file/**", "/contract/htmltopdf", "/questions/**", "/payLog/**", "/error/**" };
 
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 日志拦截器
		//registry.addInterceptor(logInterceptor).addPathPatterns("/**");
		// 登录拦截器
		registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(notLoginInterceptPaths);
	}
 
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
 
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
	
	 /* 配置JSP视图解析器,setPrefix是设置前缀，setSuffix设置后缀，按如下设置若视图名是login，那么会进入/WEB-INF/views/login.jsp页面
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }*/
 
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
 
	}
}

