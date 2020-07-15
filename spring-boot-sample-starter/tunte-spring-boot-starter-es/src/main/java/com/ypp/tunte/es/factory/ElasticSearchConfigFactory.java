package com.ypp.tunte.es.factory;

import cn.hutool.core.util.ArrayUtil;
import com.ypp.tunte.es.properties.ElasticsearchProperties;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Arrays;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/8
 **/
public class ElasticSearchConfigFactory implements FactoryBean<RestHighLevelClient>, InitializingBean, DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchConfigFactory.class);
    private ElasticsearchProperties elasticsearchProperties;
    public ElasticSearchConfigFactory(ElasticsearchProperties elasticsearchProperties){
        this.elasticsearchProperties = elasticsearchProperties;
    }

    /**
     * 高级客户端
     */
    private RestHighLevelClient restHighLevelClient;

    @Override
    public void destroy() throws Exception {
        try {
            LOGGER.info("关闭 ElasticSearch 客户端");
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        } catch (final Exception e) {
            LOGGER.error("关闭 ElasticSearch 客户端出错: ", e);
        }
    }

    @Override
    public RestHighLevelClient getObject()  {
        return this.restHighLevelClient;
    }

    @Override
    public Class<?> getObjectType() {
        return RestHighLevelClient.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.buildClient();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    protected void buildClient() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(elasticsearchProperties.getUsername(), elasticsearchProperties.getPassword()));
        RestClientBuilder builder = RestClient.builder(buildHttpHost())
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        restHighLevelClient = new RestHighLevelClient(builder);
    }

    protected  HttpHost[] buildHttpHost(){
        if (ArrayUtil.isEmpty(elasticsearchProperties.getHost())){
            throw  new BeanInitializationException(" 配置项 'spring.data.elasticsearch.host' 缺失");
        }
        int hostNumber = elasticsearchProperties.getHost().length;
        HttpHost[] hosts = new HttpHost[hostNumber];
        for (int i = 0;i<hostNumber;i++ ){
            hosts[i] = new HttpHost(elasticsearchProperties.getHost()[i],elasticsearchProperties.getPort());
        }
        return hosts;
    }
}
