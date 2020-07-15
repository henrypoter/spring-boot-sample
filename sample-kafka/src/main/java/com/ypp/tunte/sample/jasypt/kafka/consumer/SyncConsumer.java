package com.ypp.tunte.sample.jasypt.kafka.consumer;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ypp.tunte.sample.jasypt.kafka.model.KyhkArticle;
import com.ypp.tunte.sample.jasypt.kafka.model.MysqlBinlog;
import com.ypp.tunte.sample.jasypt.kafka.service.impl.SyncMysqlEsServiceImpl;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/6/19
 **/
@Component
public class SyncConsumer {

    @Resource
    private SyncMysqlEsServiceImpl syncMysqlEsService;

    @KafkaListener(topics = "mysql_es_example",groupId = "group_2")
    public void consumer(ConsumerRecord consumerRecord){
        Optional<Object> kafkaMassage = Optional.ofNullable(consumerRecord.value());
        if(kafkaMassage.isPresent()){
            Object o = kafkaMassage.get();
            System.out.println("SyncConsumer --"+o);

            JSONObject jsonObject = JSON.parseObject(o.toString());
            if ("art_article".equals(jsonObject.getString("table"))){
                MysqlBinlog<KyhkArticle> mysqlBinlog = JSON.parseObject(o.toString(),MysqlBinlog.class);
                List<KyhkArticle> kyhkArticleList = (List<KyhkArticle>) jsonObject.get("data");
                syncMysqlEsService.syncIncrement("kyhk_article",kyhkArticleList);
            }

        }

    }
}
