package com.ypp.tunte.prometheus.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/7/7
 **/
@RestController
@Slf4j
public class MyController {

    @GetMapping("/say")
    public String say(){
        for (int i=0;i<50;i++){
            log.info("say time {}",i);
        }
        return "SUCCESS";
    }
}
