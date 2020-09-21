package com.gousade.jasypt;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author woxigsd@gmail.com
 * @date 2020-8-21 15:40:42 Description: jasypt配置类
 */
@EnableEncryptableProperties
@PropertySource(name = "EncryptedProperties", value = {"classpath:application.properties",
        "classpath:/${spring.profiles.active}/application-${spring.profiles.active}.properties"})
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
