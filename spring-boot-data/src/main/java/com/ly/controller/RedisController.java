package com.ly.controller;

import com.ly.config.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: spring-data
 * @description: redis
 * @author: Mr.L
 * @create: 2019-02-10 14:08
 **/
@RestController
public class RedisController {
    @Autowired
    RedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //向redis服务器中写入值
    @GetMapping("/redis")
    public void setValueForRedis(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set("key3", "value33");
    //    redisTemplate.opsForHash().put("hash", "key2", "value2");
    }

    // 发布--订阅  发布消息
    @GetMapping("/send")
    public void sendRedisMessage(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
        logger.info("发布消息--");
        redisTemplate.convertAndSend("topic1", "redis消息");
    }


    //执行建议Lua脚本
    @GetMapping("/execute")
    public Map<String,Object> executeLua(){
        DefaultRedisScript<String> script = new DefaultRedisScript<>();
        //设置脚本
        script.setScriptText("return 'hello world~ Lua '");
        //定义返回类型 如果没有这个定义 spring不会返回结果
        script.setResultType(String.class);
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        // 执行Lua
        String result = (String) redisTemplate.execute(script, stringSerializer, stringSerializer, null);
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        return  map;

    }
}
