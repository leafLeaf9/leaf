package com.gousade.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author woxigsd@gmail.com
 * @date 2020-8-21 15:42:24 Description:jasypt加解密工具类
 */
@Component
public class JasyptUtil {

	@Autowired
//	@Qualifier("jasyptStringEncryptor")
	/**
	 * 表明使用的是JasyptConfig中的bean Field encryptor in com.gousade.jasypt.JasyptUtil
	 * required a single bean, but 2 were found 和在bean上加@Primary效果类似
	 */
	private StringEncryptor encryptor;

	public String encrypt(String value) {
		String result = encryptor.encrypt(value);
		return result;
	}

	public String decrypt(String value) {
		String result = encryptor.decrypt(value);
		return result;
	}

	public SimpleStringPBEConfig cryptor(String password) {
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(password);
		config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
		config.setStringOutputType("base64");
		return config;
	}

	/**
	 * @param password
	 * @param value
	 * @return 上面的加解密方法是使用bean中的混淆盐值，下面两个加解密方法使用传入的盐值
	 */
	public String encryptWithSalt(String password, String value) {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setConfig(cryptor(password));
		String result = encryptor.encrypt(value);
		return result;
	}

	public String decryptWithSalt(String password, String value) {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setConfig(cryptor(password));
		String result = encryptor.decrypt(value);
		return result;
	}

}
