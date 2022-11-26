package com.gousade;

import com.mzt.logapi.starter.annotation.EnableLogRecord;
import org.jodconverter.boot.autoconfigure.JodConverterLocalAutoConfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author woxigousade <woxigsd@gmail.com>
 * @date 2018/12/25
 */

@SpringBootApplication(scanBasePackages = "com.gousade", exclude = {JodConverterLocalAutoConfiguration.class})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableLogRecord(tenant = "com.gousade")
public class GousadeApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
//		SpringApplication.run(StarterManage.class, args);
        try {
            SpringApplication app = new SpringApplication(GousadeApplication.class);
            app.setBannerMode(Banner.Mode.LOG);// 使得自定义横幅可以输出到日志文件中,横幅内容在resources/banner.txt中控制
            app.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
