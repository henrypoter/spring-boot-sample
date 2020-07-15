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
public class TrainMode implements TrafficMode {
    @Override
    public TrafficCode getCode() {
        return TrafficCode.TRAIN;
    }

    @Override
    public Integer getFee() {
        return 2000;
    }
}
