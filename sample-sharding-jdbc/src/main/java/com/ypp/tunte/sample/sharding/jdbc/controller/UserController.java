package com.ypp.tunte.sample.sharding.jdbc.controller;

import com.ypp.tunte.sample.sharding.jdbc.entity.User;
import com.ypp.tunte.sample.sharding.jdbc.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/28
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id){
        return userService.getById(id);
    }

}
