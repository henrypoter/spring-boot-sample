package com.ypp.tunte.prometheus.controller;

import cn.hutool.core.util.RandomUtil;
import com.ypp.tunte.prometheus.entity.Hello;
import com.ypp.tunte.prometheus.reposity.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/7/7
 **/
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloRepository helloRepository;

    @PostMapping("/create")
    public String create(){
        Hello hello = null;
        int count = RandomUtil.randomInt(15,70);
        for (int i=0;i<count;i++){
            hello = new Hello();
            hello.setName(RandomUtil.randomString(RandomUtil.randomInt(5,9)));
            helloRepository.save(hello);
        }
    return "SUCCESS";
    }

}
