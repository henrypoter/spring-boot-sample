package com.ypp.tunte.es.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * elasticsearch配置属性
 *
 * @author yanpp
 * @createDateTime 2020/6/8
 **/
@ConfigurationProperties(prefix = "spring.data.elasticsearch")
@Data
public class ElasticsearchProperties {
    private String[] host;
    private int port;
    private String username;
    private String password;
}
