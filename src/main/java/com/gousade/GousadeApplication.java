package com.gousade;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author woxigousade <woxigsd@gmail.com>
 * @date 2018-12-25 23:25:52
 * @description SpringBoot Starter
 */
@SpringBootApplication
public class GousadeApplication extends SpringBootServletInitializer {

	/**
	 * 项目部署到外部Tomcat时需要重写的方法
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GousadeApplication.class);
	}

	public static void main(String[] args) {
//		SpringApplication.run(StarterManage.class, args);
		SpringApplication app = new SpringApplication(GousadeApplication.class);
		app.setBannerMode(Banner.Mode.LOG);// 使得自定义横幅可以输出到日志文件中,横幅内容在resources/banner.txt中控制
		app.run(args);
	}

}
