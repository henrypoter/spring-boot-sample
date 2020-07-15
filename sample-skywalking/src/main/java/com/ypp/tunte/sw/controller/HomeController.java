package com.ypp.tunte.sw.controller;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@Slf4j
public class HomeController {


    @GetMapping("/")
    public String index(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        log.info("访问主页时间:{}", LocalDateTime.now().format(formatter));
        return RandomUtil.randomString(10);
    }

}
