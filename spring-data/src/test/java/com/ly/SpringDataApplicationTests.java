package com.ly;

import com.ly.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Test
    public void contextLoads()  throws  Exception {
//        默认为 class org.apache.tomcat.jdbc.pool.DataSource
        System.out.println("数据源"+dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println("数据连接"+connection);
        connection.close();

    }
    @Test
    public void textRedis(){
        //不需要关注redis关闭和连接的问题 在redisTemplate都封装了
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForHash().put("hash", "key2", "value2");
    }
    //使用SessionCallback 接口 让RedisTemplate进行回调  就可以在同一个链接下执行多个redis命令
    @Test
    public void useSessionCallback(){
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.opsForValue().set("key1", "value1");
                redisOperations.opsForHash().put("hash", "key2", "value2");
                return null;
            }


        });

        //这个是redis流水线技术 避免redis客户端一条条命令发送给redis服务器
        //在需要执行sql的时候，才一次性的发送所有的sql去执行
        redisTemplate.executePipelined(new SessionCallback(){
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                return null;
            }
        });
    }



}

