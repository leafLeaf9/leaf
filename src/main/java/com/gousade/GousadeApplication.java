package com.gousade;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.oas.annotations.EnableOpenApi;

/** 
* @author 作者: woxi-Gisard
* @version 创建时间:2018年12月25日 下午12:25:52 
* @description SpringBoot Starter
*/
@EnableOpenApi
@EnableEncryptableProperties
@MapperScan("com.gousade.mapper")
@SpringBootApplication
public class GousadeApplication extends SpringBootServletInitializer {
	
	/** 
	*项目部署到外部Tomcat时需要重写的方法
	*/
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GousadeApplication.class);
    }
	
	public static void main(String[] args) {
//		SpringApplication.run(StarterManage.class, args);
		SpringApplication app = new SpringApplication(GousadeApplication.class);
	    app.setBannerMode(Banner.Mode.LOG);//使得自定义横幅可以输出到日志文件中,横幅内容在resources/banner.txt中控制
	    app.run(args);
	}

}
