package com.ypp.tunte.sample.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/4
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }
}
