package com.ypp.tunte.sample.es.one.service;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/1
 **/
public class TestBase {

    private String hostname="192.168.56.118";
    private int port = 9200;
    private String scheme = "http";
    RestHighLevelClient client=null;

    @Before
    public void init(){
     this.createClient();
    }

    private void createClient(){
         client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(hostname, port, scheme)));

    }

    protected void close(){
        if (client!=null){
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @After
    public void destroy(){
        close();
    }
}
