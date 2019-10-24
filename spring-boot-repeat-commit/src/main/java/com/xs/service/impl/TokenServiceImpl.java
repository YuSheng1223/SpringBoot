package com.xs.service.impl;

import com.xs.bean.RequestMessage;
import com.xs.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TokenServiceImpl
 * @Description 表单重复提交token检验service
 * @Author
 * @Date 2019-10-23 下午 2:21
 * @Version V1.0
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    StringRedisTemplate redisTemplate;

    /****
     * 单个机器环境下 ： UUID这种足够了
     * 分布式的环境  ：twitter的雪花算法、利用redis的自增、mysql的自增去保证分布式环境下token的唯一性
     * @return
     */
    @Override
    public String getToken() {

        String token = UUID.randomUUID().toString();
        //2s内视为重复提交
        redisTemplate.opsForValue().set(token,token,2, TimeUnit.SECONDS);

        return token;
    }

    /***
     * 两秒内提交同样token视为重复提交
     * @param requestMessage
     * @throws Exception
     */
    @Override
    public void checkTokenByToken(RequestMessage requestMessage) throws Exception {

        if(StringUtils.isEmpty(requestMessage.getToken())){

            log.error(" 接口请求参数中token为空 ");

            throw new Exception("接口请求参数中token为空");
        }

        if(StringUtils.isEmpty(redisTemplate.opsForValue().get(requestMessage.getToken()))){

            log.error(" token不存在 ");

            throw new Exception("token不存在");
        }

        //验证过后删除token  需要判断是否删除成功 删除失败的线程返回重复提交
        //避免多线程的情况下： 两个线程都从redis中获取了token，都又同时开始删除的情况
        Boolean delete = redisTemplate.delete(requestMessage.getToken());

        if(!delete){

            log.error(" 重复提交! ");

            throw new Exception(" 重复提交! ");
        }

        log.info(" 此请求成功提交表单  验证token成功！ ");

    }

    /***
     * 提交的session+url已经存在redis 视为重复提交
     * @param key
     * @throws Exception
     */
    @Override
    public void checkTokenBySessionAndUrl(String key) throws Exception {

        if(StringUtils.isEmpty(key)){

            log.error(" key为空 ");

            throw new Exception("key为空");
        }

        //如果缓存中存在此key 视为重复提交
        if(redisTemplate.opsForValue().get(key) == null){
            //不存在 放入redis
            redisTemplate.opsForValue().set(key,key,2, TimeUnit.SECONDS);

        }else{
            log.error(" 重复提交 ");

            throw new Exception("重复提交");
        }

        log.info(" 此请求成功提交表单 ");
    }


}
