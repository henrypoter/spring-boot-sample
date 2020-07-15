package com.ypp.tunte.sample.sharding.jdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/26
 **/
@Data
@TableName("t_address")
public class Address extends Model<Address> {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String addressName;
    private LocalDateTime createTime;

}
