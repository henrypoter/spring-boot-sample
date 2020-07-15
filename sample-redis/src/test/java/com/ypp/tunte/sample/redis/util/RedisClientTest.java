package com.ypp.tunte.sample.redis.util;

import com.ypp.tunte.sample.redis.RedisCluterApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/24
 **/

public class RedisClientTest extends RedisCluterApplicationTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test1(){

       // redisTemplate.opsForValue().set("hellokey","abcd");
        System.out.println(redisTemplate.opsForValue().get("hello"));
    }

}