package com.ypp.tunte.sample.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/2/21
 **/
@RestController
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "hello application";
    }



}
