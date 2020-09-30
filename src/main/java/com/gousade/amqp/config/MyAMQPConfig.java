package com.gousade.amqp.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-4 10:40:09
 * @description
 */

/**
 * 自动配置 1、RabbitAutoConfiguration 2、有自动配置了连接工厂ConnectionFactory；
 * 3、RabbitProperties 封装了 RabbitMQ的配置 4、 RabbitTemplate ：给RabbitMQ发送和接受消息； 5、
 * AmqpAdmin ： RabbitMQ系统管理功能组件; AmqpAdmin：创建和删除 Queue，Exchange，Binding
 * 6、@EnableRabbit + @RabbitListener 监听消息队列的内容
 */
//@EnableRabbit 此注解不加也可以正常监听@RabbitListener 估计是新版本有适应
@Configuration
public class MyAMQPConfig {

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
