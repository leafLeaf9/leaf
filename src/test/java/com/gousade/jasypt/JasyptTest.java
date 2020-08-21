package com.gousade.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

/**
* @author woxigsd@gmail.com
* @date 2020-8-21 14:34:55
* Description:生成密钥
*/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JasyptTest {
	
	@Autowired
	@Qualifier("jasyptStringEncryptor")
    StringEncryptor encryptor;
	
	@Test
	public void stringEncryptor() {
        String name = encryptor.encrypt("用户名");
        String password = encryptor.encrypt("密码");
        String url = encryptor.encrypt("jdbc:mysql://101.132.118.130:9733/gousade?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&allowMultiQueries=true");
        String ip = encryptor.encrypt("101.132.118.130");
        log.info("name: "+name);
        log.info("password: "+password);
        log.info("url: "+url);
        log.info("ip: ENC("+ip+")");
        Assert.assertTrue(name.length() > 0);
        Assert.assertTrue(password.length() > 0);
        Assert.assertTrue(url.length() > 0);
        Assert.assertTrue(ip.length() > 0);
    }

}
