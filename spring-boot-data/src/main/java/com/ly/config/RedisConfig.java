package com.ly.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ly.component.RedisMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: spring-data
 * @description: redis配置
 * @author: Mr.L
 * @create: 2019-02-08 22:30
 **/

@Configuration
@EnableCaching
public class RedisConfig {
    //redisConnectionFatory  redis链接工厂
    private RedisConnectionFactory redisConnectionFactory = null;
    //任务池
    private ThreadPoolTaskScheduler threadPoolTaskScheduler = null;

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    //创建redisConnectionFatory对象
    @Bean
    public RedisConnectionFactory initRedisConnectionFactory(){
        if (this.redisConnectionFactory != null){
            return this.redisConnectionFactory;
        }

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大空闲数
        jedisPoolConfig.setMaxIdle(30);
        //最大连接数
        jedisPoolConfig.setMaxTotal(50);
        //最大等待毫秒数
        jedisPoolConfig.setMaxWaitMillis(2000);
        //创建jedis连接工厂
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);

        //获取单机的redis配置  搭建了redis集群可以用这个 监控多个master-slave集群
      //  RedisSentinelConnection sentinelConnection = redisConnectionFactory.getSentinelConnection();

        redisConnectionFactory.setHostName("192.168.1.104");
        logger.info("redis连接"+redisConnectionFactory.getClass());
        redisConnectionFactory.setPort(6379);
        //redis客户端配置文件中没有配置密码则不需要这个
      //  redisConnectionFactory.setPassword("123456");

        this.redisConnectionFactory = redisConnectionFactory;

        return  redisConnectionFactory;

    }


    // 创建redisTemplate
    @Bean
    public RedisTemplate<String,String> initRedisTemplate(){

     /*
        这种方式值针对字符串  不针对对象
        //使用 StringRedisTemplate  默认就会设置好字符串序列化器
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        //redis默认的序列化器为JdkSerializationRedisSerializer 不用这个
        RedisSerializer stringRedisSerializer = new StringRedisSerializer();
       //设置字符串序列化器，这样Spring就会把Redis的key当做字符串处理
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);

        redisTemplate.setConnectionFactory(initRedisConnectionFactory());
        return  redisTemplate;*/;
        logger.info("设置redis的序列化规则");
        //设置序列化工具  针对java对象
        StringRedisTemplate template = new StringRedisTemplate(initRedisConnectionFactory());
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
    //创建任务池 ，运行线程等待处理redis 的消息
    @Bean
   public ThreadPoolTaskScheduler intiTaskScheduler(){
        if(threadPoolTaskScheduler != null){
            return  threadPoolTaskScheduler;
        }
       ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
       threadPoolTaskScheduler.setPoolSize(20);
       return  threadPoolTaskScheduler;

   }

    // redis消息监听容器
    @Bean
    public RedisMessageListenerContainer initRedisContainer(){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        //redis连接工厂
        container.setConnectionFactory(initRedisConnectionFactory());
        // 设置运行任务池
        container.setTaskExecutor(intiTaskScheduler());
        //定义监听渠道 名称为topic1
        ChannelTopic topic1 = new ChannelTopic("topic1");
        //使用监听器监听redis的消息
        container.addMessageListener(new RedisMessageListener(), topic1);

        return  container;
    }


    //缓存管理器
    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager(initRedisTemplate());
        //设置缓存默认过期时间 秒
        cacheManager.setDefaultExpiration(3600);
        // 设置缓存的过期时间
        Map<String,Long> expiresMap=new HashMap<>();
        //key=12h，失效时间12个小时
        expiresMap.put("12h",3600 * 12L);
        // expiresMap.put("5s",5 * 1L);
        cacheManager.setExpires(expiresMap);

        return cacheManager;
    }
}
