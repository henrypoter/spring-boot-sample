package com.ypp.tunte.sample.jasypt.minio;

import cn.hutool.core.util.RandomUtil;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.apache.commons.io.FilenameUtils;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/2/28
 **/
public class FileUploader {
    public static void main(String[] args) {
        try {

            String fileName = "E:\\\\picture\\\\id_rsa.txt";
            String objectName = RandomUtil.randomString(9)+ FilenameUtils.getExtension(fileName);
            String bucketName = "testbucket";

            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient = new MinioClient("http://192.168.2.105:9000", "keyu", "keyu4cloud");

            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(bucketName);
            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket(bucketName);
            }



            // 使用putObject上传一个文件到存储桶中。
            minioClient.putObject(bucketName,objectName, fileName);
            System.out.println("E:\\picture\\id_rsa.txt is successfully uploaded as asiaphotos.zip to `asiatrip` bucket.");
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
