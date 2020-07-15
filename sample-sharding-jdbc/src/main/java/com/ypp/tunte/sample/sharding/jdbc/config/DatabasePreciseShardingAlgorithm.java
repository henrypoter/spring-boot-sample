package com.ypp.tunte.sample.sharding.jdbc.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 分库precise算法,当前含三个库(db0,db1,db2),
 * 根据id十位数3的模，来分库
 *
 * @author yanpp
 * @createDateTime 2020/4/29
 **/
@Slf4j
public class DatabasePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        log.info("当前的数据库有: {},分片键值为：{}",collection,preciseShardingValue.getValue());
        Long tenDigit = (preciseShardingValue.getValue() / 10) % 10;
        Long mode =  tenDigit%3;
        for(String db:collection){
            if(db.endsWith(mode+"")) {
                log.info("分配至{}数据库",db);
                return db;
            }
        }

        return collection.stream().findFirst().get();
    }
}
