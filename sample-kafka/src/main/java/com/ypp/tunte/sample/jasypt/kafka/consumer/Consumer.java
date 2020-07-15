package com.ypp.tunte.sample.jasypt.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/2/21
 **/
@Component
public class Consumer {
    @KafkaListener(topics = "user",groupId = "group_1")
    public void consumer(ConsumerRecord consumerRecord){
        Optional<Object> kafkaMassage = Optional.ofNullable(consumerRecord.value());
        if(kafkaMassage.isPresent()){
            Object o = kafkaMassage.get();
            System.out.println("consumer --"+o);
        }

    }
}
