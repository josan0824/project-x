package com.yc.redis.service;


/**
 * @description 延迟队列执行器
 * @author: josan_tang
 * @createDate: 2021/12/30/030 19:41
 */
public interface RedisDelayQueueHandle<T> {
    void execute(T t);
}
