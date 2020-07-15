package com.ypp.tunte.sample.hello.service.impl;

import com.ypp.tunte.sample.hello.enums.TrafficCode;
import com.ypp.tunte.sample.hello.service.TrafficMode;
import org.springframework.stereotype.Component;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/10
 **/
@Component
public class BusMode implements TrafficMode {
    @Override
    public TrafficCode getCode() {
        return TrafficCode.BUS;
    }

    @Override
    public Integer getFee() {
        return 1000;
    }
}
