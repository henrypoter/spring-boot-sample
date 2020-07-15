package com.ypp.tunte.sample.jasypt.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/2/21
 **/
@SpringBootApplication
public class KafkaSampleApplication implements CommandLineRunner {
    public static Logger logger = LoggerFactory.getLogger(KafkaSampleApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(KafkaSampleApplication.class, args);
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final CountDownLatch latch = new CountDownLatch(3);


    @Override
    public void run(String... args) throws Exception {
        this.kafkaTemplate.send("myTopic", "foo1");
        this.kafkaTemplate.send("myTopic", "foo2");
        this.kafkaTemplate.send("myTopic", "foo3");
        latch.await(60, TimeUnit.SECONDS);
        logger.info("All received");
    }

    @KafkaListener(topics = "myTopic")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info(cr.toString());
        latch.countDown();
    }
}
