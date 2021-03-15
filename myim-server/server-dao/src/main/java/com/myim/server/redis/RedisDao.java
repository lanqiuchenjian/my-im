package com.myim.server.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RedisDao {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void setKey(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getKey(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean zddKey(String key, String value) {
        return redisTemplate.opsForZSet().add(key, value, 0);
    }

    //无序
    public Set<String> getObscureKey(String key, String obscureKey, int offset, int size) {
        RedisZSetCommands.Range range = RedisZSetCommands.Range.range().gte(obscureKey);
        RedisZSetCommands.Limit limit = RedisZSetCommands.Limit.limit().offset(offset).count(size);
        Set<String> set = redisTemplate.opsForZSet().rangeByLex(key, range, limit);
        if (set == null)
            return new HashSet<>();

        return set.stream().filter(s -> s.startsWith(obscureKey)).collect(Collectors.toSet());
    }

    public Boolean deleteKey(String key) {
        return redisTemplate.delete(key);
    }
}
