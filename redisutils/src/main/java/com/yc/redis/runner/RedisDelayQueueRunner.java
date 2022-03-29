package com.yc.redis.runner;

import com.alibaba.fastjson.JSONObject;
import com.yc.redis.model.dto.RedisDelayMessage;
import com.yc.redis.model.enums.RedisDelayQueueEnum;
import com.yc.redis.service.RedisDelayQueueHandle;
import com.yc.redis.utils.LogHelper;
import com.yc.redis.utils.RedisUtils;
import com.yc.redis.utils.SpringUtils;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: josan_tang
 * @create_date: 2022/1/27 16:37
 * @desc:启动延迟队列, 项目启动完成后开启
 * @version:
 */
@Component
public class RedisDelayQueueRunner implements CommandLineRunner {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void run(String... args) throws Exception {

        //LogHelper.writeInfoLog(this.getClass().getSimpleName(), "run", "now:" + new Date());
        RedisDelayQueueEnum[] queueEnums = RedisDelayQueueEnum.values();
        for (RedisDelayQueueEnum redisDelayQueueEnum : queueEnums) {
/*            RBlockingDeque<Map> blockingDeque = redisUtils.getBlockingDeque(redisDelayQueueEnum.getCode());
            RDelayedQueue<Map> delayedQueue = redisUtils.getDelayedQueue(blockingDeque);
            delayedQueue.offer(null, 1, TimeUnit.SECONDS);*/
            new Thread(() -> {
                while (true) {
                    Object value = redisUtils.getDelayQueue(redisDelayQueueEnum.getCode());
                    if (value != null) {
                        LogHelper.writeInfoLog(this.getClass().getSimpleName(), "run", "code：" + redisDelayQueueEnum.getCode() + ",value:" + JSONObject.toJSONString(value));
                        RedisDelayQueueHandle redisDelayQueueHandle = (RedisDelayQueueHandle) SpringUtils.getBean(redisDelayQueueEnum.getBeanId());
                        redisDelayQueueHandle.execute(JSONObject.parseObject((String) value, RedisDelayMessage.class));
                    }/* else {
                        LogHelper.writeInfoLog(this.getClass().getSimpleName(), "run", "没有数据");
                    }*/
                }
            }).start();
        }

    }
}
