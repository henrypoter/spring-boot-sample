package com.ypp.tunte.sample.jasypt.utils;

import com.ypp.tunte.sample.jasypt.constants.CommonConstant;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.properties.PropertyValueEncryptionUtils;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/11
 **/
public class JasyptUtils {
    /**
     * Jasypt生成加密结果
     *
     * @param password 配置文件中设定的加密密码 jasypt.encryptor.password
     * @param value    待加密值
     * @return
     */
    public static String encryptPwd(String password, String value) {
        PooledPBEStringEncryptor encryptOr = new PooledPBEStringEncryptor();
        encryptOr.setConfig(cryptOr(password));
        String result = encryptOr.encrypt(value);
        return result;
    }

    /**
     * 解密
     *
     * @param password 配置文件中设定的加密密码 jasypt.encryptor.password
     * @param value    待解密密文
     * @return
     */
    public static String decyptPwd(String password, String value) {
        PooledPBEStringEncryptor encryptOr = new PooledPBEStringEncryptor();
        encryptOr.setConfig(cryptOr(password));
        String result = encryptOr.decrypt(value);
        return result;
    }

    /**
     * @param password salt
     * @return
     */
    public static SimpleStringPBEConfig cryptOr(String password) {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm(StandardPBEByteEncryptor.DEFAULT_ALGORITHM);
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName(null);
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        return config;
    }

    public static void main(String[] args) {
        System.setProperty(CommonConstant.JASYPT_ENCRYPTOR_PASSWORD_PROPERTIS, CommonConstant.JASYPT_ENCRYPTOR_PASSWORD);
        StringEncryptor stringEncryptor=new StandardPBEStringEncryptor();

        stringEncryptor.encrypt("http://www.yanpingping.cn");

        System.out.println(encryptPwd(CommonConstant.JASYPT_ENCRYPTOR_PASSWORD,"http://www.yanpingping.cn"));
        System.out.println(decyptPwd(CommonConstant.JASYPT_ENCRYPTOR_PASSWORD,"6ClCT54v7ay5UOGPaH9zZK89yV/3veSxWDWokXT85qf3e2NSouIHRA=="));
    }
}
