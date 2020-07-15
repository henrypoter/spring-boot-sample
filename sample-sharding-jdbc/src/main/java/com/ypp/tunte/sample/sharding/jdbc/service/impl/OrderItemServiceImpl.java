package com.ypp.tunte.sample.sharding.jdbc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypp.tunte.sample.sharding.jdbc.entity.OrderItem;
import com.ypp.tunte.sample.sharding.jdbc.mapper.OrderItemMapper;
import com.ypp.tunte.sample.sharding.jdbc.service.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/24
 **/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
    @Override
    public List<OrderItem> findByOrderId(final long orderId) {
        return baseMapper.queryByOrderId(orderId);
    }
}
