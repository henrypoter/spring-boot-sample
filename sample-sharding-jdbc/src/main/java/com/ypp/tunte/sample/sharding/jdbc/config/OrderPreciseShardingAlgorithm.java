package com.ypp.tunte.sample.sharding.jdbc.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 订单表的precise分片算法,用于处理=和IN的分片,
 * 根据order_id的个位数决定分表，当前有3张表分别是t_order_0,t_order_1,t_order_2 ，因此为order_id个位数取3的模.
 *
 * @author yanpp
 * @createDateTime 2020/4/29
 **/
@Slf4j
public class OrderPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        log.info("订单表分片算法实现:" + JSON.toJSONString(collection) + ",preciseShardingValue:" + JSON.toJSONString(preciseShardingValue));
        String tableName = "t_order_0";

        try {
            //取出个位数
            Long tenDigit = (preciseShardingValue.getValue() / 1) % 10;
            for (String name : collection) {
                if (name.endsWith((tenDigit % 3) + "")) {
                    tableName = name;
                }
            }
        }catch (Exception e){
            log.error("订单分片配置信息错误");
        }
        log.info("分配至{}数据表",tableName);
        return tableName;
    }
}
