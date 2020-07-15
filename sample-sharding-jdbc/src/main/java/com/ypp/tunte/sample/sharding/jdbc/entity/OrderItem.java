package com.ypp.tunte.sample.sharding.jdbc.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/23
 **/
@Data
@TableName("t_order_item")
public class OrderItem extends Model<OrderItem> {
  @TableId(type = IdType.ASSIGN_ID)
  private Long   orderItemId;

  private Long orderId;

  private Long userId;

  private String status;
}
