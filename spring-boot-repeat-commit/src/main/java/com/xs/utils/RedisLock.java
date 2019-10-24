package com.xs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisLock
 * @Description
 * @Author
 * @Date 2019-10-24 下午 2:57
 * @Version V1.0
 */
public class RedisLock {

    private StringRedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * simple lock尝试获取锅的次数
     */
    private int retryCount = 3;

    /**
     * 每次尝试获取锁的重试间隔毫秒数
     */
    private int waitIntervalInMS = 100;


    public RedisLock(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 利用redis获取分布式锁(未获取锁的请求，允许丢弃!)
     *
     * @param redisKey       锁的key值
     * @param expireInSecond 锁的自动释放时间(秒)
     * @return
     * @throws Exception
     */
    public String simpleLock(final String redisKey, final int expireInSecond) throws Exception {
        String lockValue = UUID.randomUUID().toString();
        boolean flag = false;
        if (StringUtils.isEmpty(redisKey)) {
            throw new Exception(" key 的值为空");
        }
        if (expireInSecond <= 0) {
            throw new Exception("redis锁的释放时间应该大于0");
        }
        try {
            for (int i = 0; i < retryCount; i++) {
                boolean success = redisTemplate.opsForValue().setIfAbsent(redisKey, lockValue, expireInSecond, TimeUnit.SECONDS);
                if (success) {
                    //如果锁获取成功  则退出循环 执行业务逻辑
                    flag = true;
                    break;
                }
                try {
                    //未能成功获得锁 等一会 再重新尝试获取锁
                    TimeUnit.MILLISECONDS.sleep(waitIntervalInMS);

                } catch (Exception ignore) {
                    logger.warn("redis 锁 失败   :  {}" , ignore.getMessage());

                }
            }
            //重试多次仍然没有拿到锁 也就是说未获得锁的请求丢弃
            if (!flag) {
                throw new Exception(Thread.currentThread().getName() + " 此线程尝试多次未能获得锁  ");
            }
            return lockValue;
        } catch (Exception e) {
            logger.warn("尝试获取redis锁失败  :   {}" , e.getMessage());
            throw e;
        }
    }

    /**
     * 利用redis获取分布式锁(未获取锁的请求，将在timeoutSecond时间范围内，一直等待重试)
     *
     * @param redisKey       锁的key值
     * @param expireInSecond 锁的自动释放时间(秒)
     * @param timeoutSecond  未获取到锁的请求，尝试重试的最久等待时间(秒)
     * @return
     * @throws Exception
     */
    public String lock(final String redisKey, final int expireInSecond, final int timeoutSecond) throws Exception {
        String lockValue = UUID.randomUUID().toString();
        boolean flag = false;
        if (StringUtils.isEmpty(redisKey)) {
            throw new Exception(" key 的值为空");
        }
        if (expireInSecond <= 0) {
            throw new Exception(" 锁的过期时间应该大于0 ");
        }
        if (timeoutSecond <= 0) {
            throw new Exception("timeoutSecond must be greater than 0");
        }
        if (timeoutSecond >= expireInSecond) {
            throw new Exception(" 尝试获取reids锁的 时间应该大于 锁的过期时间");
        }
        try {
            long timeoutAt = System.currentTimeMillis() + timeoutSecond * 1000;
            //如果拿不到锁  就一直重试 直到超过给定时间
            while (true) {
                boolean success = redisTemplate.opsForValue().setIfAbsent(redisKey, lockValue, expireInSecond, TimeUnit.SECONDS);
                if (success) {
                    flag = true;
                    break;
                }
                if (System.currentTimeMillis() >= timeoutAt) {
                    break;
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(waitIntervalInMS);
                } catch (Exception ignore) {
                    logger.warn("redis lock fail: " + ignore.getMessage());
                }
            }
            //到达尝试获取锁的限时时间了 仍然没有获得锁
            if (!flag) {
                throw new Exception(Thread.currentThread().getName() + " 此线程在给定时间内仍然无法获取redis锁 ");
            }
            return lockValue;
        } catch (Exception e) {
            logger.warn(" 尝试获取redis锁出现异常  : {} " + e.getMessage());
            throw e;
        }
    }


    /**
     * 锁释放
     *
     * @param redisKey
     * @param lockValue
     */
    public void unlock(final String redisKey, final String lockValue) {
        if (StringUtils.isEmpty(redisKey)) {
            return;
        }
        if (StringUtils.isEmpty(lockValue)) {
            return;
        }
        try {
            String currLockVal = redisTemplate.opsForValue().get(redisKey);
            //证明 key与value是对应的 也就是说这个锁是这个key对应线程加上的
            //避免因为线程出现bug 等待锁自动释放后又再次尝试释放锁  从而释放掉别的线程的分布式锁
            if (currLockVal != null && currLockVal.equals(lockValue)) {
                boolean result = redisTemplate.delete(redisKey);
                if (!result) {
                    logger.warn(Thread.currentThread().getName() + "  删除redis锁失败 ");
                } else {
                    logger.info(Thread.currentThread().getName() + " 成功删除redis锁 redis锁 key : " + redisKey);
                }
            }
        } catch (Exception je) {
            logger.warn(Thread.currentThread().getName() + " 此线程尝试删除redis锁失败  " + je.getMessage());
        }
    }
}
