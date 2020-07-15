package com.ypp.tunte.es;

import com.ypp.tunte.es.factory.ElasticSearchConfigFactory;
import com.ypp.tunte.es.properties.ElasticsearchProperties;
import com.ypp.tunte.es.service.BaseElasticService;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/8
 **/
@Configuration
@EnableConfigurationProperties(ElasticsearchProperties.class)
public class ElasticsearchAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ElasticSearchConfigFactory restHighLevelClient(ElasticsearchProperties elasticsearchProperties) {
        return new ElasticSearchConfigFactory(elasticsearchProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(RestHighLevelClient.class)
    public BaseElasticService baseElasticService(ElasticsearchProperties elasticsearchProperties){
        return new BaseElasticService(restHighLevelClient(elasticsearchProperties).getObject());
    }
}
