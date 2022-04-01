package com.gousade.util;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author woxigousade
 * @date 2021/8/19
 */
public class RestTemplateFactory {
    private static final int CONNECT_TIMEOUT = 5000;
    private static final int READ_TIME_TIMEOUT = 30000;

    public static RestTemplate getRestTemplate() {
        return getRestTemplate(null);
    }

    public static RestTemplate getRestTemplate(Integer readTimeOut) {
        ClientHttpRequestFactory factory = simpleClientHttpRequestFactory(readTimeOut);
        return new RestTemplate(factory);
    }


    private static SimpleClientHttpRequestFactory simpleClientHttpRequestFactory(Integer readTimeOut) {
        if (readTimeOut == null) {
            readTimeOut = READ_TIME_TIMEOUT;
        }
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(CONNECT_TIMEOUT);
        factory.setReadTimeout(readTimeOut);
        return factory;
    }
}
