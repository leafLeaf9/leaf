package com.gousade.util.http.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 由于微信开放平台接口返回类型均为text/plain，而RestTemplate默认不支持此格式，需要追加一个支持text/plain的消息转换器
 * https://blog.csdn.net/kinginblue/article/details/52706155
 *
 * @author woxigousade
 * @date 2020/9/28
 */
public class MappingText2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    public MappingText2HttpMessageConverter() {
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        setSupportedMediaTypes(mediaTypes);
    }
}