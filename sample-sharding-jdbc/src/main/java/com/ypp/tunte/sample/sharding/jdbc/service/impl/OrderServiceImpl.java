package com.ypp.tunte.sample.sharding.jdbc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypp.tunte.sample.sharding.jdbc.entity.Order;
import com.ypp.tunte.sample.sharding.jdbc.mapper.OrderMapper;
import com.ypp.tunte.sample.sharding.jdbc.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/24
 **/
@Service
public class OrderServiceImpl  extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
