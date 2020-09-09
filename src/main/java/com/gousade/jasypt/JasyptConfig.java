package com.gousade.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
* @author woxigsd@gmail.com
* @date 2020-8-21 15:40:42
* Description: jasypt配置类
*/
@Configuration
public class JasyptConfig {
	
	/*@Primary
	@Bean("jasyptStringEncryptor")
	public StringEncryptor stringEncryptor() {
	    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
	    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword("yourSalt");//混淆盐值 you can input any string
	    config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
	    config.setKeyObtentionIterations("1000");
	    config.setPoolSize("1");
	    config.setProviderName("SunJCE");
	    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
	    config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
	    config.setStringOutputType("base64");
	    encryptor.setConfig(config);
	    return encryptor;
	}*/

}
