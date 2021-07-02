package com.gousade.jasypt;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author woxigsd@gmail.com
 * @date 2020-8-21 14:34:55 Description:生成密钥
 */
@Slf4j
@SpringBootTest
public class JasyptTest {

    @Autowired
//	@Qualifier("jasyptStringEncryptor")
    StringEncryptor encryptor;

    @Test
    public void stringEncryptor() {
        String url = encryptor.encrypt(
                "jdbc:mysql://ip:port/databaseName-ums?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
        String ip = encryptor.encrypt("ip");
        log.info("ip: ENC(" + ip + ")");
        Assertions.assertTrue(url.length() > 0);
        Assertions.assertTrue(ip.length() > 0);
    }

}
