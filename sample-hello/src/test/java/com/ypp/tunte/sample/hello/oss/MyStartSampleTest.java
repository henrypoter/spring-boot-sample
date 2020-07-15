package com.ypp.tunte.sample.hello.oss;

import com.ypp.tunte.aliyun.oss.service.SimpleBucketService;
import com.ypp.tunte.aliyun.oss.service.SimpleDownloadService;
import com.ypp.tunte.aliyun.oss.service.SimpleUploadService;
import com.ypp.tunte.sample.hello.HelloApplicationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;


/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/17
 **/
public class MyStartSampleTest extends HelloApplicationTest {

    @Resource
    private MyStartSample myStartSample;

    /**
     * 添加命名空间服务依赖
     */
    @Resource
    private SimpleBucketService simpleBucketService;

    /**
     * 添加简单上传服务依赖
     */
    @Resource
    public SimpleUploadService simpleUploadService;

    /**
     * 添加简单下载服务依赖
     */
    @Resource
    public SimpleDownloadService simpleDownloadService;

    @Test
    public void createBucketTest() {
        myStartSample.createBucket("tuntee");
    }

    @Test
    public void delBucketTest(){
        simpleBucketService.deleteBucket("tuntee");
    }

    @Test
    public void uploadByStreamTest(){
        simpleUploadService.uploadByStream("tunte","test/hello1.txt",new ByteArrayInputStream("iekdfdfdfdf".getBytes()));
    }

    @Test
    public void uploadByFileStreamTest(){

        simpleUploadService.uploadByFileStream("tunte","image/fileStreamTest2.jpg","E:\\temp\\images\\timg2.jpg");
    }

}