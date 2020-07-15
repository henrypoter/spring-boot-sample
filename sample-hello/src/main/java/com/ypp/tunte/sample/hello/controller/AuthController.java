package com.ypp.tunte.sample.hello.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/9
 **/
@RestController
@RequestMapping("/api/TokenAuth")
public class AuthController {

    @PostMapping(value = "/GetToken",consumes = "application/json")
    public String GetToken(@RequestBody String requestBody){

        JSONObject jsonObject=new JSONObject();

        System.out.println(requestBody);
        return jsonObject.toJSONString();
    }
}
