package com.ypp.tunte.sample.es.test.config;

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
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/16
 **/
@Configuration
public class ElasticSearchConfig implements FactoryBean<RestHighLevelClient>, InitializingBean, DisposableBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchConfig.class);

    @Value("${spring.data.elasticsearch.host}")
    private String host;
    @Value("${spring.data.elasticsearch.port}")
    private int port;
    @Value("${spring.data.elasticsearch.username}")
    private String username;
    @Value("${spring.data.elasticsearch.password}")
    private String password;
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
    public RestHighLevelClient getObject() throws Exception {
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
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        RestClientBuilder builder = RestClient.builder(new HttpHost(host, port))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        restHighLevelClient = new RestHighLevelClient(builder);
    }
}
