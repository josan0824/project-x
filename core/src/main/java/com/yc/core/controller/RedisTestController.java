package com.yc.core.controller;

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
    private RedisTemplate redisTemplate;

    @GetMapping(value = "set")
    @ApiOperation(value="set", notes ="设置redis")
    public String set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return "ok";
    }

    @GetMapping(value = "get")
    @ApiOperation(value="get", notes ="得到redis的值")
    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    @GetMapping(value = "delete")
    @ApiOperation(value="delete", notes ="删除redis的值")
    public String delete(String key) {
        redisTemplate.delete(key);
        return "ok";
    }


}
