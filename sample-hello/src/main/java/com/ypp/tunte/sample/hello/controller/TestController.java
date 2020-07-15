package com.ypp.tunte.sample.hello.controller;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/9
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/one/{f}")
    public String getTokenTest(@PathVariable("f") int f){
    /*    JSONObject postData = new JSONObject();
        postData.put("AppId","fcdd4bc2-198e-4d7b-9bf8-3bcbb2b2871e");
        postData.put("AppSecret","71DD6C03B6A9876DD03347D0E2F77E4A");
       String result = restTemplate.postForEntity("http://localhost:8089/api/TokenAuth/GetToken",postData,String.class).getBody();
        System.out.println(result);*/


       /* String url = "http://47.112.161.211:88/api/1.0/TokenAuth/GetToken";
        JSONObject postData = new JSONObject();
        postData.put("AppId","fcdd4bc2-198e-4d7b-9bf8-3bcbb2b2871e");
        postData.put("AppSecret","71DD6C03B6A9876DD03347D0E2F77E4A");
        HttpHeaders headers= new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity httpEntity=new HttpEntity<>(postData,headers);
        TokenResponse str=restTemplate.postForEntity(url,httpEntity,TokenResponse.class).getBody();
        System.out.println(str);*/

        long len=1005;

        List<String> list=new ArrayList();
        for (int i=0;i<len;i++){
            list.add("hello-"+i);
        }


        System.out.println(1005/1000);
        System.out.println(1005%1000);
/*
        List<List<String>> lists= ListUtils.fixedGrouping2(list,f);

        for (List<String> list1:lists){
            System.out.println(String.join(",",list1));
        }*/

List<String> result=Lists.newArrayList();

        List<List<String>> parts = Lists.partition(list, 999);
        parts.forEach(l ->{
            //System.out.println(l);
            result.add(Joiner.on(",").join(l));
        });
        System.out.println(result);
        return "OK";
    }

    @Data
    static class TokenResponse{
        private String data;
        private String code;
        private String status;
    }

}
