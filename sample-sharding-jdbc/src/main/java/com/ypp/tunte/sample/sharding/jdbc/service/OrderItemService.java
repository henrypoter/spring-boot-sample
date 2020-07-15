package com.ypp.tunte.sample.sharding.jdbc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ypp.tunte.sample.sharding.jdbc.entity.OrderItem;

import java.util.List;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/24
 **/
public interface OrderItemService extends IService<OrderItem> {
    List<OrderItem> findByOrderId(final long orderId);
}
