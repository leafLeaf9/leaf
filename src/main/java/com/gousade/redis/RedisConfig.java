package com.gousade.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 配置Jackson2JsonRedisSerializer序列化策略
     */
    private Jackson2JsonRedisSerializer<Object> serializer() {
        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
                Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常，用于验证反序列化的实际子类型
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(factory);
        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        // 值采用json序列化
        template.setValueSerializer(this.serializer());
        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(this.serializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean("enableTransactionRedisTemplate")
    public RedisTemplate<String, Object> enableTransactionRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(factory);
        // 开启事务
        template.setEnableTransactionSupport(true);
        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        // 值采用json序列化
        template.setValueSerializer(this.serializer());
        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(this.serializer());
        template.afterPropertiesSet();
        return template;
    }

    /**
     * @param redisConnectionFactory
     * @return 自定义缓存管理器，可以指定key的失效时间，并设置Jackson2序列化方式
     */
    @Primary // 如果有多个缓存管理器的话，指定默认的缓存管理器
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return new RedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                this.getRedisCacheConfigurationWithTtl(86400), // 默认策略，未配置的 key 会使用这个
                this.getRedisCacheConfigurationMap() // 指定 key 策略
        );
    }

    /**
     * @param redisConnectionFactory
     * @return 使用spring的默认方式创建缓存管理器
     */
    @Bean
    public RedisCacheManager springDefaultCacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 这个被注释的写法类似上面的自定义缓存管理器，也是通过config和map设置不同缓存的生存时间，这里不使用这种方法。
		/*RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();  
		  // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
		   config = config.entryTtl(Duration.ofMinutes(1))     // 设置缓存的默认过期时间，也是使用Duration设置
		           .disableCachingNullValues();     // 不缓存空值
		
		   // 设置一个初始化的缓存空间set集合
		   Set<String> cacheNames =  new HashSet<>();
		   cacheNames.add("my-redis-cache1");
		   cacheNames.add("my-redis-cache2");
		
		   // 对每个缓存空间应用不同的配置
		   Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
		   configMap.put("my-redis-cache1", config);
		   configMap.put("my-redis-cache2", config.entryTtl(Duration.ofSeconds(120)));
		
		   RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory)     // 使用自定义的缓存配置初始化一个cacheManager
		           .initialCacheNames(cacheNames)  // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
		           .withInitialCacheConfigurations(configMap)
		           .build();*/
        RedisCacheManager cacheManager = RedisCacheManager.create(redisConnectionFactory);
        return cacheManager;
    }

    /**
     * @return 为特定@Cacheable(value="redis@Cacheable-config4")设定过期时间，默认是上面的86400秒
     */
    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put("redis@Cacheable", this.getRedisCacheConfigurationWithTtl(100000));
        return redisCacheConfigurationMap;
    }

    /**
     * @param seconds
     * @return 配置Jackson2JsonRedisSerializer序列化方式，并指定缓存失效时间
     */
    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofSeconds(seconds))
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(this.serializer()))
                .disableCachingNullValues();
        return redisCacheConfiguration;
    }

    /**
     * 生成key，value::方法名(参数)，例如redis@Cacheable-config6::getUser()
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append(target.getClass().getSimpleName());
//            stringBuilder.append(".");
//            stringBuilder.append(method.toString().substring(0, method.toString().indexOf("(")));
//            stringBuilder.append(target.getClass().getName());
            stringBuilder.append(method.getName());
            stringBuilder.append("(");
            for (Object obj : params) {
                stringBuilder.append(obj.toString());
            }
            stringBuilder.append(")");

            return stringBuilder.toString();
        };
    }

    /**
     * 对hash类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * 对redis字符串类型数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * 对链表类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * 对无序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * 对有序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}