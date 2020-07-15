package com.ypp.tunte.sample.batch.config;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/4
 **/
@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {
    @Override
    public void setDataSource(DataSource dataSource) {

    }

}
