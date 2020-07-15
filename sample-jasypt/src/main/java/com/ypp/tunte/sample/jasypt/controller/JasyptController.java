package com.ypp.tunte.sample.jasypt.controller;

import com.ypp.tunte.sample.jasypt.properties.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/11
 **/
@RestController
@AllArgsConstructor
public class JasyptController {

    private final User user;

    @GetMapping("/user")
    public User getUserConfig(){
        return user;
    }

}
