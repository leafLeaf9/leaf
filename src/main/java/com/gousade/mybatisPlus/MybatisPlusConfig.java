package com.gousade.mybatisPlus;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author woxigsd@gmail.com
 * @date 2020-9-1 11:34:27
 * @description
 */
@MapperScan("com.gousade.mapper")
@Configuration
public class MybatisPlusConfig {

	/**
	 * 乐观锁插件 since 3.4.0
	 *
	 * @return please use {@link MybatisPlusInterceptor}
	 * {@link OptimisticLockerInnerInterceptor} since 3.4.0
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
		return interceptor;
	}

}
