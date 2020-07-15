package com.ypp.tunte.sample.sharding.jdbc.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/23
 **/
@Configuration
@MapperScan("com.ypp.tunte.sample.sharding.jdbc.mapper")
public class MybatisPlusConfig {


}
