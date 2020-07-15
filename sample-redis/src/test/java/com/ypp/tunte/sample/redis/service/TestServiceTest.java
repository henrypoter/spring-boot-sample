package com.ypp.tunte.sample.redis.service;


import com.google.common.collect.Maps;
import com.ypp.tunte.sample.redis.RedisCluterApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Map;


/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/26
 **/
@Slf4j
public class TestServiceTest extends RedisCluterApplicationTest {

    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    @Test
    public void setHashTest(){
        Map<String,Object> map = Maps.newHashMap();
        map.put("name","Tom");
        map.put("age",20);
        redisTemplate.opsForHash().putAll("hash_key",map );
    }

    @Test
    public void getHashTest(){
      Object o =  redisTemplate.opsForHash().get("hash_key","age");

      log.info("{}",o);

    }

    @Test
    public void incrementTest(){
        redisTemplate.opsForHash().increment("hash_key","age",-1);
    }

}