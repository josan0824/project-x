package com.yc.core.controller;

import com.yc.core.model.dto.RedisMapObj;
import com.yc.core.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("redis")
@Api(tags = "redis")
public class RedisTestController {

    @Autowired
    private RedisUtils redisUtils;

    //-------------------String------------------------
    @GetMapping(value = "setString")
    @ApiOperation(value="setString", notes ="设置redis")
    public String set(String key, String value) {
        redisUtils.set(key, value);
        return "ok";
    }

    @GetMapping(value = "getString")
    @ApiOperation(value="getString", notes ="得到redis的值")
    public String get(String key) {
        return (String) redisUtils.get(key);
    }

    /**
     * 根据key删除redis中数据
     * @param key
     * @return
     */
    @GetMapping(value = "deleteStr")
    @ApiOperation(value="deleteStr", notes ="删除redis的值")
    public String delete(String key) {
        redisUtils.del(key);
        return "ok";
    }

    @GetMapping(value = "setValueByExpire")
    @ApiOperation(value="setValueByExpire", notes ="通过过期时间设置值")
    public boolean setValueByExpire(String key, String value, long expire) {
        return redisUtils.set(key, value, expire);
    }

    @GetMapping(value = "getExpire")
    @ApiOperation(value="getExpire", notes ="获取获取时间")
    public long getExpire(String key) {
        return redisUtils.getExpire(key);
    }

    /**
     * 单纯设置key的过期时间
     * @param key
     * @param expire
     * @return -1表示永久不失效
     */
    @GetMapping(value = "setExpire")
    @ApiOperation(value="setExpire", notes ="单纯设置key的过期时间")
    public boolean setExpire(String key, long expire) {
        return redisUtils.expire(key, expire);
    }

    @GetMapping(value = "hasKey")
    @ApiOperation(value="hasKey", notes ="判断是否有key")
    public boolean hasKey(String key) {
        return redisUtils.hasKey(key);
    }

    /**
     * 增n TODO 报Error in execution; nested exception is io.lettuce.core.RedisCommandExecutionException: ERR value is not an integer or out of range
     * @param key
     * @param delta 需要大于0
     * @return
     */
    @GetMapping(value = "incr")
    @ApiOperation(value="incr", notes ="增n")
    public long incr(String key, long delta) {
        return redisUtils.incr(key, delta);
    }

    /**
     * 减n
     * @param key
     * @param delta 需要大于0
     * @return
     */
    @GetMapping(value = "decr")
    @ApiOperation(value="decr", notes ="减N")
    public long decr(String key, long delta) {
        return redisUtils.decr(key, delta);
    }

    // ================================Map=================================
    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值 需要实例化
     * @return true 成功 false失败
     */
    @PostMapping(value = "hSet")
    @ApiOperation(value="hSet", notes ="向一张hash表中放入数据,如果不存在将创建")
    public boolean hSet(String key, String item, @RequestBody RedisMapObj value) {
        return redisUtils.hset(key, item, value);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值 需要实例化
     * @return true 成功 false失败
     */
    @PostMapping(value = "hSetByExpire")
    @ApiOperation(value="hSetByExpire", notes ="向一张hash表中放入数据,如果不存在将创建")
    public boolean hSet(String key, String item, @RequestBody RedisMapObj value, long expire) {
        return redisUtils.hset(key, item, value, expire);
    }

    /**
     * HashGet
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    @GetMapping(value = "hGet")
    @ApiOperation(value="hGet", notes ="hGet")
    public Object hGet(String key, String item) {
        return redisUtils.hget(key, item);
    }

    /**
     *
     * @param key 键
     * @return true 成功 false 失败
     */
    @GetMapping(value = "hmset")
    @ApiOperation(value="hmset", notes ="hmset")
    public Object hmset(String key) {
        Map<String, Object> map  = new HashMap();
        RedisMapObj redisMapObj1 = new RedisMapObj();
        redisMapObj1.setName("josan");
        redisMapObj1.setAge(24);
        map.put("user", redisMapObj1);

        RedisMapObj redisMapObj2 = new RedisMapObj();
        redisMapObj2.setName("josan2");
        redisMapObj2.setAge(242);
        map.put("user2", redisMapObj1);
        return redisUtils.hmset(key, map);
    }

    @GetMapping(value = "hmget")
    @ApiOperation(value="hmget", notes ="hmget")
    public Map<Object, Object> hmget(String key) {
        return redisUtils.hmget(key);
    }


    /**
     *
     * @param key 键
     * @return true 成功 false 失败
     */
    @GetMapping(value = "hmsetByExpire")
    @ApiOperation(value="hmsetByExpire", notes ="hmsetByExpire")
    public Object hmsetByExpire(String key, long time) {
        Map<String, Object> map  = new HashMap();
        RedisMapObj redisMapObj1 = new RedisMapObj();
        redisMapObj1.setName("josan");
        redisMapObj1.setAge(24);
        map.put("user", redisMapObj1);

        RedisMapObj redisMapObj2 = new RedisMapObj();
        redisMapObj2.setName("josan2");
        redisMapObj2.setAge(242);
        map.put("user2", redisMapObj1);
        return redisUtils.hmset(key, map, time);
    }

    @GetMapping(value = "hdel")
    @ApiOperation(value="hdel", notes ="hdel")
    public void hdel(String key, String item) {
        redisUtils.hdel(key, item);
    }

    @GetMapping(value = "hHasKey")
    @ApiOperation(value="hHasKey", notes ="hHasKey")
    public void hHasKey(String key, String item) {
        redisUtils.hHasKey(key, item);
    }

    @GetMapping(value = "hincr")
    @ApiOperation(value="hincr", notes ="hincr")
    public double hincr(String key, String item, double by) {
        return redisUtils.hincr(key, item, by);
    }

    @GetMapping(value = "hdecr")
    @ApiOperation(value="hdecr", notes ="hdecr")
    public double hdecr(String key, String item, double by) {
        return redisUtils.hdecr(key, item, by);
    }

}
