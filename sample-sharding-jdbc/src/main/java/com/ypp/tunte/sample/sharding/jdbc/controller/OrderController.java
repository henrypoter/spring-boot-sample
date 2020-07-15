package com.ypp.tunte.sample.sharding.jdbc.controller;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.ypp.tunte.sample.sharding.jdbc.entity.Order;
import com.ypp.tunte.sample.sharding.jdbc.entity.OrderItem;
import com.ypp.tunte.sample.sharding.jdbc.service.OrderItemService;
import com.ypp.tunte.sample.sharding.jdbc.service.OrderService;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/27
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    public OrderItemService orderItemService;

    @PostMapping("/create")
    public String createOrder(){
        Order order = new Order();
        String baseString= "匕首三棱刀三棱刮刀半圆刮刀侵刀扒皮刀羊骨刀猎刀弹簧刀武器及其部件各种类型的军用枪支民用枪支运动枪猎枪信号枪麻醉注射枪仿真武器炸弹手榴弹子弹照明弹教练弹烟幕弹炸药引信雷管导火索及其他爆炸物品纵火器材";
         Long userId =     Math.abs(RandomUtil.randomLong(1000L,9999999999L)) ;
         String status = RandomUtil.randomString(2);

        order.setUserId(userId);
        order.setStatus(status);

        orderService.save(order);

        List<OrderItem> orderItems = Lists.newArrayList();

        OrderItem orderItem=new OrderItem();
        orderItem.setOrderId(order.getOrderId());
        orderItem.setUserId(userId);
        orderItem.setStatus(status);
        orderItems.add(orderItem);


        orderItem=new OrderItem();
        orderItem.setOrderId(order.getOrderId());
        orderItem.setUserId(userId);
        orderItem.setStatus(status);
        orderItems.add(orderItem);
        orderItemService.saveBatch(orderItems);
        return "SUCCESS";
    }

    @GetMapping("/item/{id}")
    public List<OrderItem> listItemById(@PathVariable("id") long id){
        return orderItemService.findByOrderId(id);
    }

}
