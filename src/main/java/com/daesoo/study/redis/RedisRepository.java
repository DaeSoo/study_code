package com.daesoo.study.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisRepository {
    private final RedisTemplate<String, String> redisTemplate;

    public Boolean lock(Long key){ //lock 설정
        return redisTemplate
                .opsForValue()
                .setIfAbsent(generateKey(key), "lock", Duration.ofMillis(3_000));
    }

    public Boolean unlock(Long key) { // lock 해제
        return redisTemplate.delete(generateKey(key));
    }

    private String generateKey(Long key){
        return key.toString();
    }

}
