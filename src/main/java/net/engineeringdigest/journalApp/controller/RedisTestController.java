package net.engineeringdigest.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/redis/test")
    public String testRedis() {

        redisTemplate.opsForValue().set("name", "Siddharth");

        return redisTemplate.opsForValue().get("name");
    }
}