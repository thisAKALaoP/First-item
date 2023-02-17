package com.taiji.erp.base.config;

import com.taiji.erp.common.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @ClassName: RedisConfig
 * @Description: TODO(一句话描述该类的功能)
 * @Author: yangcw
 * @Date: 2020/4/19 19:40
 */
@Configuration
public class RedisConfig {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Bean
    public RedisUtil redisUtil(){
        return new RedisUtil(redisTemplate);
    }
}
