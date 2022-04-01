package com.gousade.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @author woxigousade
 * @date 2021/6/10
 */
public class SerializerUtil {

    private volatile static Jackson2JsonRedisSerializer<Object> serializer;

    private SerializerUtil() {
    }

    public static Jackson2JsonRedisSerializer<Object> getSerializer() {
        //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (serializer == null) {
            //类对象加锁
            synchronized (SerializerUtil.class) {
                if (serializer == null) {
                    serializer = initSerializer();
                }
            }
        }
        return serializer;
    }

    private static Jackson2JsonRedisSerializer<Object> initSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会抛出异常，用于验证反序列化的实际子类型
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }

}
