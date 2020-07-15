package com.ypp.tunte.sample.sharding.jdbc.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/4/29
 **/
@Slf4j
public class MyPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        log.info("collection:" + JSON.toJSONString(collection) + ",preciseShardingValue:" + JSON.toJSONString(preciseShardingValue));

        for (String name : collection) {
            if (name.endsWith(preciseShardingValue.getValue() % collection.size() + "")) {
                log.info("return name:"+name);
                return name;
            }
        }
        return null;
    }
}
