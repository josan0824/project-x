package com.yc.redis.controller;

import com.alibaba.fastjson.JSONObject;
import com.yc.redis.model.dto.RedisDelayMessage;
import com.yc.redis.model.enums.RedisDelayQueueEnum;
import com.yc.redis.utils.IdWorker;
import com.yc.redis.utils.LogHelper;
import com.yc.redis.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value="redis分布式锁", notes ="redis分布式锁")
    @GetMapping("/getLock")
    public String getLock() throws InterruptedException {
        String lockKey = "user";
        String lockValue = String.valueOf(idWorker.nextId());
        long outtime = 60L;
        try {
            if (redisUtils.addLock(lockKey, lockValue, outtime)) {
                LogHelper.writeInfoLog(this.getClass().getSimpleName(), "getLock", "获得锁");
                //模拟业务处理
                Thread.sleep(5000);
            } else {
                LogHelper.writeInfoLog(this.getClass().getSimpleName(), "getLock", "未获取到锁");
            }
        } finally {
            LogHelper.writeInfoLog(this.getClass().getSimpleName(), "getLock", "释放锁");
            redisUtils.unlock(lockKey, lockValue);
        }
        return "ok";
    }

}
