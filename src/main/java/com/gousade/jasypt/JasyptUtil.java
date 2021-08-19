package com.gousade.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author woxigsd@gmail.com
 * @date 2020-8-21 15:42:24
 */
@Component
public class JasyptUtil {

    /**
     * 表明使用的是JasyptConfig中的bean Field encryptor in com.gousade.jasypt.JasyptUtil
     * required a single bean, but 2 were found 和在bean上加@Primary效果类似
     */
    @Autowired
//	@Qualifier("jasyptStringEncryptor")
    private StringEncryptor encryptor;

    public String encrypt(String value) {
        return encryptor.encrypt(value);
    }

    public String decrypt(String value) {
        return encryptor.decrypt(value);
    }

    public SimpleStringPBEConfig encryptor(String password) {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName(null);
        config.setProviderClassName(null);
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        return config;
    }

    /**
     * @param password password salt
     * @param value    value to be encrypted
     * @return 上面的加解密方法是使用bean中的混淆盐值，下面两个加解密方法使用传入的盐值
     */
    public String encryptWithSalt(String password, String value) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(encryptor(password));
        return encryptor.encrypt(value);
    }

    public String decryptWithSalt(String password, String value) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(encryptor(password));
        return encryptor.decrypt(value);
    }

}
