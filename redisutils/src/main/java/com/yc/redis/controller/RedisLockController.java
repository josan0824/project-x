package com.yc.redis.controller;

import com.alibaba.fastjson.JSONObject;
import com.yc.redis.model.dto.RedisDelayMessage;
import com.yc.redis.model.enums.RedisDelayQueueEnum;
import com.yc.redis.utils.IdWorker;
import com.yc.redis.utils.LogHelper;
import com.yc.redis.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: josan_tang
 * @create_date: 2022/1/27 15:54
 * @desc: Redis分布式锁
 * @version:
 */
@RestController
@RequestMapping("/delayqueue")
@Api(tags = "redis分布式锁")
public class RedisLockController {

    @Resource
    private IdWorker idWorker;

    @Resource
    private Redisson redisson;

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value="redis分布式锁", notes ="redis分布式锁")
    @GetMapping("/getLock")
    public String getRedisLock() throws InterruptedException {
        LogHelper.writeInfoLog(this.getClass().getSimpleName(), "getLock", "进入" +  Thread.currentThread().getName());
        String lockKey = "getRedisLock";
        String lockValue = String.valueOf(idWorker.nextId());
        long outtime = 60L;
        try {
            //加锁的key、value、超时时间
            if (redisUtils.addLock(lockKey, lockValue, outtime)) {
                LogHelper.writeInfoLog(this.getClass().getSimpleName(), "getLock", "获得锁"  + Thread.currentThread().getName());
                //模拟业务处理
                Thread.sleep(10000);
            } else {
                LogHelper.writeInfoLog(this.getClass().getSimpleName(), "getLock", "未获取到锁"  + Thread.currentThread().getName());
            }
        } finally {
            LogHelper.writeInfoLog(this.getClass().getSimpleName(), "getLock", "释放锁"  + Thread.currentThread().getName());
            redisUtils.unlock(lockKey, lockValue);
        }
        return "ok";
    }

    @ApiOperation(value="redission分布式锁", notes ="redission分布式锁")
    @GetMapping("/getRedissionLock")
    public String getRedissionLock() throws InterruptedException {
        LogHelper.writeInfoLog(this.getClass().getSimpleName(), "getRedissionLock", "进入" +  Thread.currentThread().getName());
        String lockKey = "getRedisLock";
        RLock lock = redisson.getLock(lockKey);
        try {
            //等待时间，超时时间，单位
            //超时时间如果不设置，会使用看门狗进行自动续约，默认30，每30/3秒进行一次重写设置到30
            //lock.tryLock(20, TimeUnit.SECONDS)
            if (lock.tryLock(20, 30, TimeUnit.SECONDS)) {
                LogHelper.writeInfoLog(this.getClass().getSimpleName(), "getRedissionLock", "得到分布式锁" +  Thread.currentThread().getName());
                Thread.sleep(50000);
            } else {
                LogHelper.writeInfoLog(this.getClass().getSimpleName(), "getRedissionLock", "未获得分布式锁" +  Thread.currentThread().getName());
            }
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                LogHelper.writeInfoLog(this.getClass().getSimpleName(), "getRedissionLock", "释放分布式锁" +  Thread.currentThread().getName());
                lock.unlock();
            }
        }
        return "ok";
    }

}
