package com.ypp.tunte.sample.sb.es.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/17
 **/
@Configuration
@EnableElasticsearchRepositories(basePackages = {"com.ypp.tunte.**.repository"})
public class ElasticsearchConfig {

}
