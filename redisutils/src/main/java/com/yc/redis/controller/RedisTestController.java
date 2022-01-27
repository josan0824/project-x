package com.yc.redis.controller;

import com.yc.redis.model.dto.RedisMapObj;
import com.yc.redis.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    // ============================set=============================

    /**
     * 设置set的值
     * @param key
     * @param values
     * @return
     */
    @GetMapping(value = "sSet")
    @ApiOperation(value="sSet", notes ="sSet")
    public long sSet(String key, String values) {
        return redisUtils.sSet(key, values);
    }

    /**
     * 得到set的值
     * @param key
     * @return
     */
    @GetMapping(value = "sGet")
    @ApiOperation(value="sGet", notes ="sGet")
    public Set<Object> sGet(String key) {
        return redisUtils.sGet(key);
    }

    /**
     * 根据value从一个set中查询,是否存在
     * @param key 键
     * @param value 值
     * @return true 存在 false不存在
     */
    @GetMapping(value = "sHasKey")
    @ApiOperation(value="sHasKey", notes ="sHasKey")
    public boolean sHasKey(String key, String value) {
        return redisUtils.sHasKey(key, value);
    }

    /**
     * 将set数据放入缓存
     * @param key 键
     * @param time 时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    @GetMapping(value = "sSetAndTime")
    @ApiOperation(value="sSetAndTime", notes ="sSetAndTime")
    public long sSetAndTime(String key, String values, long time) {
        return redisUtils.sSetAndTime(key, time, values);
    }

    /**
     * 获取set缓存的长度
     * @param key 键
     * @return
     */
    @GetMapping(value = "sGetSetSize")
    @ApiOperation(value="sGetSetSize", notes ="sGetSetSize")
    public long sGetSetSize(String key) {
        return redisUtils.sGetSetSize(key);
    }

    /**
     * 移除值为value的
     * @param key 键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    @GetMapping(value = "setRemove")
    @ApiOperation(value="setRemove", notes ="setRemove")
    public long setRemove(String key, String values) {
        return redisUtils.setRemove(key, values);
    }


    // ===============================list=================================
    /**
     * 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束 0 到 -1代表所有值
     * @return
     */
    @GetMapping(value = "lGet")
    @ApiOperation(value="lGet", notes ="lGet")
    public List<Object> lGet(String key, long start, long end) {
        return redisUtils.lGet(key, start, end);
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    @GetMapping(value = "lSet")
    @ApiOperation(value="lSet", notes ="lSet")
    public boolean lSet(String key, String value) {
        return redisUtils.lSet(key, value);
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    @GetMapping(value = "lSetByExpire")
    @ApiOperation(value="lSetByExpire", notes ="lSetByExpire")
    public boolean lSetByExpire(String key, Object value, long time) {
        return redisUtils.lSet(key, value, time);
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    @GetMapping(value = "lSetByList")
    @ApiOperation(value="lSetByList", notes ="lSetByList")
    public boolean lSetByList(String key, List<Object> value) {
        return redisUtils.lSet(key, value);
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    @GetMapping(value = "lSetByListAndExpire")
    @ApiOperation(value="lSetByListAndExpire", notes ="lSetByListAndExpire")
    public boolean lSetByListAndExpire(String key, List<Object> value, long time) {
        return redisUtils.lSet(key, value, time);
    }

    /**
     * 通过索引 获取list中的值
     * @param key 键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    @GetMapping(value = "lGetIndex")
    @ApiOperation(value="lGetIndex", notes ="lGetIndex")
    public Object lGetIndex(String key, long index) {
        return redisUtils.lGetIndex(key, index);
    }

    /**
     * 获取list缓存的长度
     * @param key 键
     * @return
     */
    @GetMapping(value = "lGetListSize")
    @ApiOperation(value="lGetListSize", notes ="lGetListSize")
    public long lGetListSize(String key) {
        return redisUtils.lGetListSize(key);
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return
     */
    @GetMapping(value = "lUpdateIndex")
    @ApiOperation(value="lUpdateIndex", notes ="lUpdateIndex")
    public boolean lUpdateIndex(String key, long index, String value) {
        return redisUtils.lUpdateIndex(key, index, value);
    }

    /**
     * 移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    @GetMapping(value = "lRemove")
    @ApiOperation(value="lRemove", notes ="lRemove")
    public long lRemove(String key, long count, String value) {
        return redisUtils.lRemove(key, count, value);
    }

    //----------------------zset------------------------


    /**
     * 向zset中插入值
     * @param key
     * @param value
     * @param score
     * @return
     */
    @GetMapping(value = "zAdd")
    @ApiOperation(value="zAdd", notes ="zAdd")
    public boolean zAdd(String key, String value, double score) {
        return redisUtils.zAdd(key, value, score);
    }

    /**
     * 从zset中获取值
     * @param key
     * @param start
     * @param end
     * @return
     */
    @GetMapping(value = "zRangeWithScores")
    @ApiOperation(value="zRangeWithScores", notes ="zRangeWithScores")
    public Set<ZSetOperations.TypedTuple> zRangeWithScores(String key, long start, long end) {
        return redisUtils.zRangeWithScores(key, start, end);
    }

    /**
     * 从zset中删除值
     * @param key
     * @param objects
     * @return
     */
    @GetMapping(value = "zRemove")
    @ApiOperation(value="zRemove", notes ="zRemove")
    public Long zRemove(String key, String objects) {
        return redisUtils.zRemove(key, objects);
    }
}
