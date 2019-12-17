package com.gousade.config;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling//此做法和将@EnableScheduling这个注解写在springboot入口处的效果是一样的，两个写一个即可。
public class SchedulerTask {
	
	/**
	 * CRON表达式 含义 :
		0 0 12 * * ?” 每天中午十二点触发 
		0 15 10 ? * *” 每天早上10：15触发 
		0 15 10 * * ?” 每天早上10：15触发 
		0 15 10 * * ? *” 每天早上10：15触发 
		0 15 10 * * ? 2005” 2005年的每天早上10：15触发 
		0 * 14 * * ?” 每天从下午2点开始到2点59分每分钟一次触发 
		0 0/5 14 * * ?” 每天从下午2点开始到2：55分结束每5分钟一次触发 
		0 0/5 14,18 * * ?” 每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发 
		0 0-5 14 * * ?” 每天14:00至14:05每分钟一次触发 
		0 10,44 14 ? 3 WED” 三月的每周三的14：10和14：44触发 
		0 15 10 ? * MON-FRI” 每个周一、周二、周三、周四、周五的10：15触发
	 * @throws IOException 
	 */
	
	@Scheduled(cron = "*/6 * * * * ?")
	public void pushDataScheduled(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		System.out.println("现在时间：" + dateFormat.format(new Date()));
	}
}
