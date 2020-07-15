package com.ypp.tunte.aliyun.oss.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.StringUtils;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.google.common.base.Preconditions;
import com.ypp.tunte.aliyun.oss.exception.UploadExcption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 简单上传服务
 *
 * @author yanpp
 * @createDateTime 2020/4/17
 **/
public class SimpleUploadService extends BaseAliyunOSSService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleUploadService.class);
    private OSS ossClient = null;

    public SimpleUploadService(String endpoint, String accessKeyId, String accessKeySecret){
        super(endpoint, accessKeyId, accessKeySecret);
    }

    public String uploadByStream(String bucketName,String objectName, InputStream inputStream){
       return uploadByStream(bucketName,objectName,inputStream,null);
    }

    public String uploadByStream(String bucketName,String objectName, byte[] bytes){
        return uploadByStream(bucketName,objectName,new ByteArrayInputStream(bytes),null);
    }

    public String uploadByStream(String bucketName,String objectName, String content){
        return uploadByStream(bucketName,objectName,new ByteArrayInputStream(content.getBytes()),null);
    }

    /**
     * 上传网络流
     * @param bucketName   命名空间名
     * @param objectName   对象名
     * @param url       网络URL
     * @return
     */
    public String uploadByNetworkStream(String bucketName,String objectName, URL url)  {
        InputStream inputStream;
        try {
            inputStream =  url.openStream();
        }catch (IOException ioEx){
            throw new UploadExcption("网络流");
        }
        return uploadByStream(bucketName,objectName,inputStream,null);
    }

    /**
     * 上传网络流
     * @param bucketName   命名空间名
     * @param objectName   对象名
     * @param url       网络URL字符串
     * @return
     */
    public String uploadByNetworkStream(String bucketName,String objectName, String url)  {
        try {
            return uploadByNetworkStream(bucketName,objectName,new URL(url));
        } catch (MalformedURLException e) {
            throw new UploadExcption("url 不正确");
        }
    }

    /**
     * 上传文件流
     * @param bucketName
     * @param objectName
     * @param filePath
     * @return
     */
    public String uploadByFileStream(String bucketName,String objectName,String filePath){
        try {
            InputStream inputStream = new FileInputStream(filePath);
            return uploadByStream(bucketName,objectName,inputStream);
        } catch (FileNotFoundException e) {
            throw new UploadExcption("文件不存在,file:"+filePath);
        }
    }

    public String uploadByStream(String bucketName, String objectName, InputStream inputStream, ObjectMetadata metadata){
        //checkBucketName(bucketName);
        checkObjectName(objectName);
        Preconditions.checkArgument(inputStream!=null,"upload stream can not null");
        try{
            ossClient =  getOSSClient();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,objectName,inputStream);

            if (metadata!=null){
                putObjectRequest.setMetadata(metadata);
            }
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            LOGGER.debug("upload result :{}",result);
            return accessUrl(bucketName, objectName);
        }finally {
            closeClient(ossClient);
        }
    }








}
