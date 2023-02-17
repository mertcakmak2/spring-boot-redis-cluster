package com.example.rediscluster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cache")
public class CacheController {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    CacheController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @GetMapping(path = "/set")
    public String setCache(){
        System.out.println("set cache");
        redisTemplate.opsForValue().set("mert", "Trabzon", Duration.ofMinutes(2L));
		redisTemplate.opsForValue().set("ali", "Eskisehir", Duration.ofMinutes(2L));
		redisTemplate.opsForValue().set("mustafa", "Sivas", Duration.ofMinutes(2L));
		redisTemplate.opsForValue().set("veli", "Ankara", Duration.ofMinutes(2L));
        return "cache set.";
    }

    @GetMapping(path = "/get")
    public Map getCache(){
        System.out.println("get cache");
        var city1 = redisTemplate.opsForValue().get("mert");
        var city2 = redisTemplate.opsForValue().get("ali");
        var city3 = redisTemplate.opsForValue().get("mustafa");
        var city4 = redisTemplate.opsForValue().get("veli");

        Map map = new HashMap<String, String>();
        map.put("mert", city1);
        map.put("ali", city2);
        map.put("mustafa", city3);
        map.put("veli", city4);

        return map;
    }
}
