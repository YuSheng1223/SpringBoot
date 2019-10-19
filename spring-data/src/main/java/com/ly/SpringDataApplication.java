package com.ly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

//为这个包下的接口默认加上@Mapper
@MapperScan(basePackages = "com.ly.mapper")
@SpringBootApplication
public class SpringDataApplication {

    @Autowired
    private RedisTemplate redisTemplate;
    //被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，类似于Serclet的inti()方法
//    @PostConstruct
//    public void init(){
//        initRedisTemplate();
//    }

/*    private void initRedisTemplate(){
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }*/

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

}

