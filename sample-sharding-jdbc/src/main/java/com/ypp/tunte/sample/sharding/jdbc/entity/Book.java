package com.ypp.tunte.sample.sharding.jdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/23
 **/
@Data
@TableName("t_book")
public class Book extends Model<Book> {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private int count;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date publishDate;
}