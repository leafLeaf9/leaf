package com.gousade.util;

import com.gousade.util.http.converter.MappingText2HttpMessageConverter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author woxigousade
 * @date 2020/9/22
 */
public class RemoteObjectUtil {
    private static final Map<String, Object> map = new HashMap<>();

    public static RestTemplate getRestTemplate() {
        return getRestTemplate(5 * 1000, 15 * 1000);
    }

    public static RestTemplate getRestTemplate(int connectTimeOut, int readTimeOut) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeOut);
        requestFactory.setReadTimeout(readTimeOut);
        return new RestTemplate(requestFactory);
    }

    public static RestTemplate getDefaultRestTemplate() {
        return new RestTemplate();
    }

    public static RestTemplate getTextSupportedRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingText2HttpMessageConverter());
        return restTemplate;
    }

    public static void addHeaders(RestTemplate restTemplate, Map<String, String> map) {
        restTemplate.getInterceptors().add((httpRequest, bytes, clientHttpRequestExecution) -> {
            HttpHeaders headers = httpRequest.getHeaders();
            map.forEach(headers::add);
            return clientHttpRequestExecution.execute(httpRequest, bytes);
        });
    }
}