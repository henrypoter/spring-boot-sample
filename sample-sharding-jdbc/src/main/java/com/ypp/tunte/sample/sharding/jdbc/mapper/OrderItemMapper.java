package com.ypp.tunte.sample.sharding.jdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypp.tunte.sample.sharding.jdbc.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/24
 **/
public interface OrderItemMapper extends BaseMapper<OrderItem> {

    List<OrderItem> queryByOrderId(@Param("orderId") long orderId);

}
