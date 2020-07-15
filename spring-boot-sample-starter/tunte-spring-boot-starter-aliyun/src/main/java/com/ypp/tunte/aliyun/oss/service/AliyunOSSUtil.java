package com.ypp.tunte.aliyun.oss.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.ypp.tunte.aliyun.oss.properties.OSSClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/13
 **/

public class AliyunOSSUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliyunOSSUtil.class);

    private OSS ossClient;
    private OSSClientProperties ossClientProperties;

    public AliyunOSSUtil(OSSClientProperties ossClientProperties){
        this.ossClientProperties = ossClientProperties;
        this.ossClient = new OSSClientBuilder().build(ossClientProperties.getEndPoint(), ossClientProperties.getAccessKeyId(), ossClientProperties.getAccessKeySecret());
    }

    public boolean upload(File file){
        LOGGER.info("------OSS文件上传开始--------,{}",file.getName());
        createBucket(ossClientProperties.getBucketName());
        return false;
    }

    public void createBucket(String bucketName){
        createBucket(bucketName,CannedAccessControlList.PublicRead);
    }

    public void createBucket(String bucketName,CannedAccessControlList cannedAccessControlList){
        if (!ossClient.doesBucketExist(bucketName)) {
            LOGGER.info("Creating bucket {}" , bucketName);
            ossClient.createBucket(bucketName);
            CreateBucketRequest createBucketRequest= new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(cannedAccessControlList);
            ossClient.createBucket(createBucketRequest);
        }
    }




}
