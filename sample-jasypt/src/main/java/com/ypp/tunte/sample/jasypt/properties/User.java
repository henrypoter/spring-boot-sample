package com.ypp.tunte.sample.jasypt.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/11
 **/
@Data
@Component
@ConfigurationProperties(prefix = "user")
public class User {
    private String name;
    private String age;
    private String url;
}
