package com.ypp.tunte.sample.hello.oss;

import com.ypp.tunte.aliyun.oss.service.SimpleBucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/17
 **/
@Component
public class MyStartSample {
    @Autowired
    private SimpleBucketService simpleBucketService;

    public void createBucket(String bucketName){
        simpleBucketService.createBucket(bucketName);
    }
}
