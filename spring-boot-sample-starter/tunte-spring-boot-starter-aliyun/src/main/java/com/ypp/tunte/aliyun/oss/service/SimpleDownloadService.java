package com.ypp.tunte.aliyun.oss.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/17
 **/
public class SimpleDownloadService extends BaseAliyunOSSService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleDownloadService.class);
    private OSS ossClient = null;
    public SimpleDownloadService(String endpoint, String accessKeyId, String accessKeySecret) {
        super(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 下载
     * @param bucketName 命名空间名
     * @param objectName 对象名称
     * @return
     */
    public InputStream getForStream(String bucketName,String objectName){
        checkObjectName(objectName);
        try{
            ossClient = getOSSClient();
            OSSObject ossObject = ossClient.getObject(bucketName, objectName);
            return  ossObject.getObjectContent();
        }finally {
            closeClient(ossClient);
        }
    }

    /**
     * 下载到本地文件系统
     * @param bucketName
     * @param objectName
     * @param filePath
     */
    public void getForLocalFS(String bucketName,String objectName,String filePath){
        try{
            ossClient = getOSSClient();
            ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(filePath));
        }finally {
            closeClient(ossClient);
        }
    }

}
