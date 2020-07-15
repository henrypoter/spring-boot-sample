package com.ypp.tunte.sample.redis.controller;

import com.ypp.tunte.sample.redis.lock.annotation.NoRepeatSubmitLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/26
 **/
@RestController
@RequestMapping("/books")
public class BookController {
    @NoRepeatSubmitLock(expireTime = 1000 * 10)
    @GetMapping
    public String query(@RequestParam String token) {
        return "success - " + token;
    }
}
