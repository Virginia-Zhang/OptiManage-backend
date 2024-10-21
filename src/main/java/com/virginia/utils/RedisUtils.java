package com.virginia.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    private static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }

    public static void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public static Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public static Boolean removeValue(String key) {
        return redisTemplate.delete(key);
    }

    public static Boolean expire(String key, Long timeOut, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeOut, timeUnit);
    }
}
