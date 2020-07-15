package com.ypp.tunte.aliyun.oss.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.StringUtils;
import com.google.common.base.Preconditions;

/**
 * aliyun oss 基础
 *
 * @author yanpp
 * @createDateTime 2020/4/13
 **/
public abstract   class BaseAliyunOSSService {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;


    private static final int BUCKET_NAME_LEN_MIN = 3;
    private static final int BUCKET_NAME_LEN_MAX = 63;

    public static final String FILE_DELIMITER = "/";

    private static  final String ACCESS_PROTOCOL = "https://";


    public BaseAliyunOSSService(String endpoint,String accessKeyId,String accessKeySecret){
        this.endpoint = endpoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
    }

    /**
     * 创建OSSClient实例
     * @return
     */
    protected OSS getOSSClient(){
       return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 关闭OSSClient
     * @param client
     */
    protected void closeClient(final OSS client){
        if (client!=null){
            client.shutdown();
        }
    }

    /**
     * 存储空间名（bucketName）检测
     * @param bucketName
     */
    protected void checkBucketName(String bucketName){
        /**
         * 只能包括小写字母、数字和短横线（-）。
         * 必须以小写字母或者数字开头和结尾。
         * 长度必须在 3–63 字节之间。
         */
        String pattern = "^(?=[a-z0-9])([a-z0-9/-]{4,61})([a-z0-9])$";
        Preconditions.checkArgument(!StringUtils.isNullOrEmpty(bucketName),"bucketName cant not null or empty");
        Preconditions.checkArgument(bucketName.length()>=BUCKET_NAME_LEN_MIN && bucketName.length()<=BUCKET_NAME_LEN_MAX,String.format("bucketName character length is %d to %d ",BUCKET_NAME_LEN_MIN,BUCKET_NAME_LEN_MAX));
        Preconditions.checkArgument(bucketName.matches(pattern),"Illegal  bucketName");
    }

    protected void checkObjectName(String objectName){
        Preconditions.checkArgument(!StringUtils.isNullOrEmpty(objectName),"object name can not empty");
        Preconditions.checkArgument(!objectName.startsWith(FILE_DELIMITER),"object name not allow start with '/'");
    }

    protected String accessUrl(String bucketName,String objectName){
        return ACCESS_PROTOCOL.concat(bucketName).concat(endpoint).concat(FILE_DELIMITER).concat(objectName);
    }

    public static void main(String[] args) {
        String pattern = "^(?=[a-z0-9])([a-z0-9/-]{4,61})([a-z0-9])$";

        Preconditions.checkArgument(("tuntee").matches(pattern),"HELLO");
    }

}

