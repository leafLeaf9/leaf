package com.gousade.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/** 
* @author 作者: woxi-Gisard
* @version 创建时间:2019年12月18日 下午6:29:28 
* 类说明:CRON表达式 含义 :
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
		cron表达式的星期中1为周日SUN 2为周一MON 以此类推 7为周六SAT，
		但是此处使用org.springframework.scheduling.support.CronSequenceGenerator类，
		其中0,1,2,3,4,5,6分别表示SUN,MON,TUE,WED,THU,FRI,SAT 所以周一到周五应该是1,2,3,4,5或者MON,TUE,WED,THU,FRI  
		Sunday can be represented as 0 or 7 0和7都可以代表周日 
		参考http://www.manongjc.com/detail/15-zlvwmipbrrbfjht.html
*/
@Component
@EnableScheduling//此做法和将@EnableScheduling这个注解写在springboot入口处的效果是一样的，两个写一个即可。
@EnableAsync
public class SchedulerTask {
	
	@Async
	@Scheduled(cron = "*/6 * * * * ?")
	public void pushDataScheduled(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		System.out.println("现在时间：" + dateFormat.format(new Date()));
	}
}
