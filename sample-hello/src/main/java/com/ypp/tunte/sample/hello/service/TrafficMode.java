package com.ypp.tunte.sample.hello.service;

import com.ypp.tunte.sample.hello.enums.TrafficCode;

/**
 *  交通方式
 *
 * @author yanpp
 * @createDateTime 2020/3/10
 **/
public interface TrafficMode {
    /**
     * 查询交通方式编码
     * @return 编码
     */
    TrafficCode getCode();

    /**
     * 查询交通方式的费用，单位：分
     * @return 费用
     */
    Integer getFee();
}
