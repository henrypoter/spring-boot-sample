package com.ypp.tunte.sample.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/25
 **/
@Configuration
@ConditionalOnProperty(name = "spring.redis.sentinel.enable",havingValue = "true")
public class LettuceSentinelConfig {
    /*@Value("${spring.redis.sentinel.master}")
    private String sentinelName;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.sentinel.nodes}")
    private String[] sentinels;


    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisSentinelConfiguration rsc= new RedisSentinelConfiguration();
        rsc.setMaster(sentinelName);
        List<RedisNode> redisNodeList= new ArrayList<RedisNode>();
        for (String sentinel : sentinels) {
            String[] nodes = sentinel.split(":");
            redisNodeList.add(new RedisNode(nodes[0], Integer.parseInt(nodes[1])));
        }
        rsc.setSentinels(redisNodeList);
        rsc.setPassword(password);
        return new LettuceConnectionFactory(rsc);
    }


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }*/
}
