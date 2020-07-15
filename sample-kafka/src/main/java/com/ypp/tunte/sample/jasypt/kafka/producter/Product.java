package com.ypp.tunte.sample.jasypt.kafka.producter;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.ypp.tunte.sample.jasypt.kafka.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/2/21
 **/
@Component
@Slf4j
public class Product {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(String name) {
        User u = new User();
        u.setName(name);
        u.setAge(RandomUtil.randomInt(1, 100));
        ListenableFuture<SendResult<String,String>> future= kafkaTemplate.send("user", JSON.toJSONString(u));
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("消息发送失败,{}",throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                log.info("消息发送成功");
            }
        });



    }
}
