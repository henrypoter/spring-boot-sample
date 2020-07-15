package com.ypp.tunte.sample.es.one.service;

import com.ypp.tunte.sample.es.one.SpringEsDataSample;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringEsDataSample.class)
@Slf4j
public class BankServiceTest {
    @Autowired
    private RestHighLevelClient highLevelClient;


    @Test
    public void isdocuexitTest(){
        GetRequest getRequest = new GetRequest("bank");
        getRequest.id("1");
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        try {
          boolean exist = highLevelClient.exists(getRequest, RequestOptions.DEFAULT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
