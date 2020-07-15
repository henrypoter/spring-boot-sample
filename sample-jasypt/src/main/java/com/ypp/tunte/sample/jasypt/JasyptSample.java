package com.ypp.tunte.sample.jasypt;

import com.ypp.tunte.sample.jasypt.constants.CommonConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/11
 **/
@SpringBootApplication
public class JasyptSample {

    public static void main(String[] args) {
        //设置jasypt根密码
        System.setProperty(CommonConstant.JASYPT_ENCRYPTOR_PASSWORD_PROPERTIS, CommonConstant.JASYPT_ENCRYPTOR_PASSWORD);

        SpringApplication.run(JasyptSample.class,args);
    }


}
