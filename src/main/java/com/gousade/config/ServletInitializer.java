package com.gousade.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.gousade.GousadeApplication;

/**
 * @author woxigousade <woxigsd@gmail.com>
 * @date 2018-12-30 16:38:59
 * @description
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * 项目部署到外部Tomcat时需要重写的方法
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GousadeApplication.class);
	}

}
