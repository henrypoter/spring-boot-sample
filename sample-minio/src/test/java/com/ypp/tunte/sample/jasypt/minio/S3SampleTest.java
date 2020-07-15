package com.ypp.tunte.sample.jasypt.minio;

import cn.hutool.core.util.RandomUtil;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.ypp.tunte.sample.jasypt.minio.listener.MyProgressListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/2/28
 **/
@Slf4j
public class S3SampleTest {

    private AmazonS3 s3Client=null;
    String bucketName = "testbucket";

    @Before
    public void init(){
        AWSCredentials credentials = new BasicAWSCredentials("keyu", "keyu4cloud");
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setSignerOverride("AWSS3V4SignerType");
         s3Client = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://192.168.2.105:9000", Regions.US_EAST_1.name()))
                .withPathStyleAccessEnabled(true)
                .withClientConfiguration(clientConfiguration)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }


    @Test
    public void putObjectTest(){
        log.info("开始上传...");
        long startMillis = System.currentTimeMillis();


        String filePath = "E:\\temp\\hello.txt";

        String keyName  = RandomUtil.randomString(9)+"."+ FilenameUtils.getExtension(filePath);

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,keyName,new File(filePath));
        putObjectRequest.setGeneralProgressListener(new MyProgressListener());

        PutObjectResult putObjectResult = s3Client.putObject(putObjectRequest);

        log.info(putObjectResult.getContentMd5());

        log.info("上传花费了:{}MS",System.currentTimeMillis()-startMillis);

    }

    @Test
    public void multipartUploadTest(){


        String keyName = "s3sample";
        String filePath = "E:\\temp\\hello.txt";

        String uploadId = "";

        File file = new File(filePath);
        long contentLength = file.length();
        long partSize = 5 * 1024 * 1024; // Set part size to 5 MB.

        try {
            List<PartETag> partETags = new ArrayList<PartETag>();

            // Initiate the multipart upload.
            InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(bucketName, keyName);
            InitiateMultipartUploadResult initResponse = s3Client.initiateMultipartUpload(initRequest);
            uploadId = initResponse.getUploadId();
            // Upload the file parts.
            long filePosition = 0;
            for (int i = 1; filePosition < contentLength; i++) {
                // Because the last part could be less than 5 MB, adjust the part size as needed.
                partSize = Math.min(partSize, (contentLength - filePosition));

                // Create the request to upload a part.
                UploadPartRequest uploadRequest = new UploadPartRequest()
                        .withBucketName(bucketName)
                        .withKey(keyName)
                        .withUploadId(uploadId)
                        .withPartNumber(i)
                        .withFileOffset(filePosition)
                        .withFile(file)
                        .withPartSize(partSize);



                // Upload the part and add the response's ETag to our list.
                UploadPartResult uploadResult = s3Client.uploadPart(uploadRequest);
                partETags.add(uploadResult.getPartETag());

                filePosition += partSize;
            }

            // Complete the multipart upload.
            CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(bucketName, keyName,   uploadId, partETags);
            s3Client.completeMultipartUpload(compRequest);
        }catch (Exception e){
            AbortMultipartUploadRequest abortMultipartUploadRequest=new AbortMultipartUploadRequest(bucketName,keyName,uploadId);
            s3Client.abortMultipartUpload(abortMultipartUploadRequest);
        }



    }

    @Test
    public void deleteObjectTest(){
        String[] keyNames = new String[]{"0qd2fgyrd.txt","qhrbmsdcp.txt","ftzgix41jtxt","in75otwuttxt","t21co4pnhtxt","t7z6gse0ptxt","s3sample","fp5z66632.jpg"};

        for (int i=0;i<keyNames.length;i++) {
            s3Client.deleteObject(bucketName, keyNames[i]);
        }
    }




}
