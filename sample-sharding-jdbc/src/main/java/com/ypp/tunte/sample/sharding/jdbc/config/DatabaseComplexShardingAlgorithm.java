package com.ypp.tunte.sample.sharding.jdbc.config;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;


import java.util.Collection;

/**
 * 复合算法分片
 *
 * @author yanpp
 * @createDateTime 2020/4/29
 **/
@Slf4j
public class DatabaseComplexShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {


    private final static String SHARDING_KEY_ID="id";
    private final static String SHARDING_KEY_ORDER_ID="order_id";
    @Override
    public Collection<String> doSharding(Collection<String> collection, ComplexKeysShardingValue<Long> complexKeysShardingValue) {
        log.info("collection : {}, complexKeysShardingValue :{}",collection, JSON.toJSONString(complexKeysShardingValue) );

        Long shardingValue = 0L;
        if (complexKeysShardingValue.getColumnNameAndShardingValuesMap().containsKey(SHARDING_KEY_ORDER_ID)){
            shardingValue = complexKeysShardingValue.getColumnNameAndShardingValuesMap().get(SHARDING_KEY_ORDER_ID).stream().findFirst().get();
        }else if(complexKeysShardingValue.getColumnNameAndShardingValuesMap().containsKey(SHARDING_KEY_ID)){
            shardingValue = complexKeysShardingValue.getColumnNameAndShardingValuesMap().get(SHARDING_KEY_ID).stream().findFirst().get();
        }
        Long tenDigit = (shardingValue / 10) % 10;
        Long mode =  tenDigit%3;
        for(String db:collection){
            if(db.endsWith(mode+"")) {
                log.info("分配至{}数据库",db);
                return Lists.newArrayList(db);
            }
        }

        return Lists.newArrayList("db0");
    }
}
