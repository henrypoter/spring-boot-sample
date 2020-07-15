package com.ypp.tunte.aliyun.oss;

import com.ypp.tunte.aliyun.oss.properties.OSSClientProperties;
import com.ypp.tunte.aliyun.oss.service.SimpleBucketService;
import com.ypp.tunte.aliyun.oss.service.SimpleDownloadService;
import com.ypp.tunte.aliyun.oss.service.SimpleUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 自动配置类
 *
 * @author yanpp
 * @createDateTime 2020/4/13
 **/
@Configuration
@EnableConfigurationProperties(OSSClientProperties.class)
@ConditionalOnProperty(prefix = "tunte.aliyun-oss", name = "enable",havingValue = "true")
@Slf4j
public class OSSClientAutoConfiguration {

    @Resource
    private OSSClientProperties ossClientProperties;

    @Bean(name = "simpleBucketService")
    @ConditionalOnMissingBean
    public SimpleBucketService simpleBucketService(){
        return new SimpleBucketService(ossClientProperties.getEndPoint(),ossClientProperties.getAccessKeyId(),ossClientProperties.getAccessKeySecret());
    }

    @Bean(name = "simpleUploadService")
    @ConditionalOnMissingBean
    public SimpleUploadService simpleUploadService(){
        return new SimpleUploadService(ossClientProperties.getEndPoint(),ossClientProperties.getAccessKeyId(),ossClientProperties.getAccessKeySecret());
    }

    @Bean(name = "simpleDownloadService")
    @ConditionalOnMissingBean
    public SimpleDownloadService simpleDownloadService(){
        return new SimpleDownloadService(ossClientProperties.getEndPoint(),ossClientProperties.getAccessKeyId(),ossClientProperties.getAccessKeySecret());
    }
}
