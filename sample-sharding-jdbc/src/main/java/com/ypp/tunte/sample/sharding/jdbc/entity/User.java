package com.ypp.tunte.sample.sharding.jdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/26
 **/
@Data
@TableName("t_user")
public class User  extends Model<User> {
    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;

    private String userName;

    private String userNamePlain;

    private String pwd;

    private String assistedQueryPwd;
}
