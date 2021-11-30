package com.gousade.test;

import cn.hutool.extra.spring.SpringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @author woxigousade
 * @date 2021/11/22
 */
@SpringBootTest
public class MyInterfaceTest {
    @Test
    public void test() {
        Map<String, MyInterface> beansOfType = SpringUtil.getBeansOfType(MyInterface.class);
        beansOfType.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v.getClass().getName());
        });
    }
}
