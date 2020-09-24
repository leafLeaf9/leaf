package com.gousade.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author woxigsd@gmail.com
 * @date 2020-7-4 10:43:46
 * @description 配置redis存储session,86400秒,即一天,session在redis中的过期时间
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400)
public class SessionConfig {
}