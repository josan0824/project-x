package com.yc.redis.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.yc.redis.model.dto.RedisDelayMessage;
import com.yc.redis.service.RedisDelayQueueHandle;
import com.yc.redis.utils.LogHelper;
import org.springframework.stereotype.Service;

/**
 * @description redis 延迟队列消费
 * @author: lukas_tan
 * @createDate: 2021/12/30/030 19:42
 */
@Service
public class Key1DelayQueueHandleImpl implements RedisDelayQueueHandle<RedisDelayMessage> {

    @Override
    public void execute(RedisDelayMessage message) {
        LogHelper.writeErrLog(this.getClass().getSimpleName(), "execute",
                "message:" + JSONObject.toJSONString(message));
    }

}
