package com.yc.core.controller;

import com.yc.core.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
@Api(tags = "redis")
public class RedisTestController {

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping(value = "set")
    @ApiOperation(value="set", notes ="设置redis")
    public String set(String key, String value) {
        redisUtils.set(key, value);
        return "ok";
    }

    @GetMapping(value = "get")
    @ApiOperation(value="get", notes ="得到redis的值")
    public String get(String key) {
        return (String) redisUtils.get(key);
    }

    @GetMapping(value = "delete")
    @ApiOperation(value="delete", notes ="删除redis的值")
    public String delete(String key) {
        redisUtils.del(key);
        return "ok";
    }


}
