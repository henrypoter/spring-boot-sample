package com.ypp.tunte.sample.jasypt.minio;

import cn.hutool.core.util.RandomUtil;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Before;
import org.junit.Test;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/2/28
 **/

@Slf4j
public class MinioClientTest {

    MinioClient minioClient = null;
    String BUCKET_NAME = "testbucket";
    @Before
    public void init() throws InvalidPortException, InvalidEndpointException {
        minioClient= new MinioClient("http://192.168.2.105:9000", "keyu", "keyu4cloud");
    }

    @Test
    public void putObjectTest() {
        try {
            log.info("开始上传...");
            long startMillis = System.currentTimeMillis();
            String fileName = "E:\\temp\\b.txt";
            String objectName = RandomUtil.randomString(9)+"."+ FilenameUtils.getExtension(fileName);
            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(BUCKET_NAME);
            if (isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket(BUCKET_NAME);
            }

            File file = new File(fileName);



            // 使用putObject上传一个文件到存储桶中。
            minioClient.putObject(BUCKET_NAME, objectName, fileName,FileUtils.sizeOf(file),new HashMap<>(),null,null);

            log.info("上传花费了:{}MS",System.currentTimeMillis()-startMillis);

            System.out.println("E:\\picture\\id_rsa.txt is successfully uploaded as asiaphotos.zip to `asiatrip` bucket.");
        } catch (MinioException e) {
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
