package com.ypp.tunte.sample.jasypt.kafka.controller;

import cn.hutool.core.util.RandomUtil;
import com.ypp.tunte.sample.jasypt.kafka.producter.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/2/21
 **/
@RestController
public class ProducterController {

    private static  final  String BASE_WORD="下面推荐给大家一款带有可视化页面有没有为无法直观地查看需要设置展示格式";

    @Autowired
    private Product product;
    @GetMapping("/send")
    public String send(){


        sendMessage1();
        //sendMessage2();

        return "ok";

    }

    private void sendMessage1(){
        product.send(RandomUtil.randomString(BASE_WORD,RandomUtil.randomInt(5,10)));
    }

    private void sendMessage2(){
        product.send(RandomUtil.randomString(BASE_WORD,8));
    }
}
