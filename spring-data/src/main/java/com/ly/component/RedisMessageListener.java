package com.ly.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @program: spring-data
 * @description: Redis消息监听器
 * @author: Mr.L
 * @create: 2019-02-10 21:25
 **/
@Component
public class RedisMessageListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 得到消息后的处理方法 message代表redis发送过来的消息 bytes是渠道名称
    @Override
    public void onMessage(Message message, byte[] bytes) {
        // 消息体
        String body = new String(message.getBody());
        // 渠道名称
        String topic = new String(bytes);

        logger.info("redis消息监听器接收到的消息体    "+body);
        logger.info("redis消息监听器--渠道名称"+topic);

    }
}
