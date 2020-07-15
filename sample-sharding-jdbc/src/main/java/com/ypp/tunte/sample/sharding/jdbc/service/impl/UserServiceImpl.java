package com.ypp.tunte.sample.sharding.jdbc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypp.tunte.sample.sharding.jdbc.entity.User;
import com.ypp.tunte.sample.sharding.jdbc.mapper.UserMapper;
import com.ypp.tunte.sample.sharding.jdbc.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/28
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
