package com.cloud.erp.utils;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

import lombok.extern.slf4j.Slf4j;

/**
 * @author YANGKAIQI1
 */
@Slf4j
public class JasyptUtil {

    public static final String PBEWITHHMACSHA_512_ANDAES_256 = "PBEWITHHMACSHA512ANDAES_256";

    /**
     * Jasypt生成加密结果
     * 
     * @param password
     *            配置文件中设定的加密密码 jasypt.encryptor.password
     * @param value
     *            待加密值
     * @return
     */
    public static String encyptPwd(String password, String value) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(cryptor(password));
        return encryptor.encrypt(value);
    }

    /**
     * 解密
     * 
     * @param password
     *            配置文件中设定的加密密码 jasypt.encryptor.password
     * @param value
     *            待解密密文
     * @return
     */
    public static String decyptPwd(String password, String value) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(cryptor(password));
        encryptor.decrypt(value);
        return encryptor.decrypt(value);
    }

    public static SimpleStringPBEConfig cryptor(String password) {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm(PBEWITHHMACSHA_512_ANDAES_256);
        config.setKeyObtentionIterations("1000");
        config.setPoolSize(1);
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        return config;
    }

    public static void main(String[] args) {

        // 加密 若修改了第一个参数加密password记得在配置文件同步修改
        System.out.println(encyptPwd("xboot", "123456"));
        // 解密
        System.out.println(decyptPwd("xboot", "PYVnAYh+j5C3jkMV1d+myj6JzDaUk7pcfTWUaYsvQdEVkuvIVf7Y0mOU9XkffxT8"));
    }
}
