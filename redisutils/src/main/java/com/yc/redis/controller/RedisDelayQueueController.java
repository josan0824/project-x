package com.yc.redis.controller;

import com.alibaba.fastjson.JSONObject;
import com.yc.redis.model.dto.RedisDelayMessage;
import com.yc.redis.model.enums.RedisDelayQueueEnum;
import com.yc.redis.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: josan_tang
 * @create_date: 2022/1/27 15:54
 * @desc: Redis延时队列
 * @version:
 */
@RestController
@RequestMapping("/delayqueue")
@Api(tags = "redis延时队列")
public class RedisDelayQueueController {

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value="加入延时队列", notes ="加入延时队列")
    @GetMapping("/addDelayQueue")
    public String addDelayQueue(long delay, String content) {
        RedisDelayMessage redisDelayMessage = new RedisDelayMessage();
        redisDelayMessage.setCode(1);
        redisDelayMessage.setMessage("自定义消息");
        redisDelayMessage.setData(content);
        redisUtils.addDelayQueue(redisDelayMessage, delay, TimeUnit.SECONDS, RedisDelayQueueEnum.KEY_1.getCode());
        return "发送成功";
    }

    @ApiOperation(value="得到延时队列", notes ="得到延时队列")
    @GetMapping("/getDelayQueue")
    public String getDelayQueue() {
        Map map = redisUtils.getDelayQueue(RedisDelayQueueEnum.KEY_1.getCode());
        return JSONObject.toJSONString(map);
    }
}
