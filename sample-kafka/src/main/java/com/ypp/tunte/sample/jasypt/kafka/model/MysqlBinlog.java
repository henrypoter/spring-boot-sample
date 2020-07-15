package com.ypp.tunte.sample.jasypt.kafka.model;

import lombok.Data;

import java.util.List;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/19
 **/
@Data
public class MysqlBinlog<T> {

    private List<T> data;
    private String table;
    private long ts;
    private String type;

}
