package com.test.amqp;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testString(){
//        redisTemplate.opsForValue().set("name","zhu");
//        Object name = redisTemplate.opsForValue().get("name");
//        System.out.println(name);
        ConfigurableApplicationContext run = SpringApplication.run(RedisTest.class);
        RedisTemplate bean = run.getBean(RedisTemplate.class);
        System.out.println(bean);
    }
}
