package com.ffw.springbootredis;

import com.ffw.springbootredis.Utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Autowired
    private RedisUtils redisUtils;
    @Test
    void contextLoads() {
        redisUtils.set("myKey","myValue");
        System.out.println(redisUtils.get("myKey"));
    }

}
