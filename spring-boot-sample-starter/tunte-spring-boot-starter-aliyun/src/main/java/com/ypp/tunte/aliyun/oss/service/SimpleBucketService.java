package com.ypp.tunte.aliyun.oss.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 基本服务
 *
 * @author yanpp
 * @createDateTime 2020/4/13
 **/
public class SimpleBucketService extends BaseAliyunOSSService{

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleBucketService.class);
    private OSS ossClient = null;

    public SimpleBucketService(String endpoint, String accessKeyId, String accessKeySecret) {
        super(endpoint, accessKeyId, accessKeySecret);
    }



    /*********** bucket function***********/

    /**
     * 创建存储空间
     * @param bucketName 存储空间名称
     */
    public void createBucket(String bucketName){
        this.createBucket(bucketName,CannedAccessControlList.PublicRead,StorageClass.Standard);
    }

    /**
     * 创建存储空间
     * @param bucketName 存储空间名称
     * @param acl 访问控制权限
     */
    public void createBucket(String bucketName,CannedAccessControlList acl){
        this.createBucket(bucketName,acl,StorageClass.Standard);
    }

    /**
     * 创建存储空间
     * @param bucketName 存储空间名称
     * @param acl  访问控制权限
     * @param storageClass 存储类型
     */
    public void createBucket(String bucketName, CannedAccessControlList acl, StorageClass storageClass){
        checkBucketName(bucketName);
        try {
            ossClient = getOSSClient();
            if (!ossClient.doesBucketExist(bucketName)) {
                LOGGER.info("create new bucket {}",bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(acl);
                createBucketRequest.setStorageClass(storageClass);
                ossClient.createBucket(createBucketRequest);
            }
        }catch (Exception e){
            LOGGER.error("create bucket error--{}",e.getMessage());
        }finally {
            closeClient(ossClient);
        }

    }

    /**
     * 列举所有的存储空间
     * @return
     */
    public List<Bucket> listBuckets(){
        try{
              ossClient = getOSSClient();
              return ossClient.listBuckets();
        }finally {
            closeClient(ossClient);
        }
    }

    /**
     * 列举指定前缀的存储空间
     * @param bucketPrefix
     * @return
     */
    public List<Bucket> listBuckets(String bucketPrefix){
        try {
            ossClient = getOSSClient();
            ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
            listBucketsRequest.setPrefix(bucketPrefix);
            BucketList bucketList = ossClient.listBuckets(listBucketsRequest);
            return bucketList.getBucketList();
        }finally {
            closeClient(ossClient);
        }
    }


    /**
     * 获取存储空间的信息
     * @param bucketName
     * @return
     */
    public BucketInfo getBucketInfo(String bucketName){
        try{
            ossClient = getOSSClient();
            return   ossClient.getBucketInfo(bucketName);
        }finally {
            closeClient(ossClient);
        }
    }

    /**
     * 设置存储空间访问权限
     * @param bucketName 存储空间名
     * @param acl 访问权限
     */
    public void setBucketAcl(String bucketName,CannedAccessControlList acl){
        try{
            ossClient = getOSSClient();
            ossClient.setBucketAcl(bucketName, acl);
        }finally {
            closeClient(ossClient);
        }
    }


    public void deleteBucket(String bucketName){
        try{
            ossClient = getOSSClient();
            ossClient.deleteBucket(bucketName);
        }finally {
            closeClient(ossClient);
        }

    }




}
