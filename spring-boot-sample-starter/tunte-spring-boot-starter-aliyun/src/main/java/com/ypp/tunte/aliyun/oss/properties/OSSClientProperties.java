package com.ypp.tunte.aliyun.oss.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/13
 **/
@ConfigurationProperties(prefix = "tunte.aliyun-oss")
public class OSSClientProperties {

    private String endPoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
