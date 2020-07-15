package com.ypp.tunte.sample.sharding.jdbc.service.impl;


import com.ypp.tunte.sample.sharding.jdbc.SampleShardingJdbcApp;
import com.ypp.tunte.sample.sharding.jdbc.entity.Order;
import com.ypp.tunte.sample.sharding.jdbc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/23
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SampleShardingJdbcApp.class})// 指定启动类
@Slf4j
public class OrderServiceImplTest  {

    @Resource
    private OrderService orderService;

    @Test
    public void saveTest(){
        Order order = new Order();

        orderService.save(order);
    }

}