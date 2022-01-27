package com.yc.redis.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @description 定义延迟队列建
 * @author: lukas_tan
 * @createDate: 2021/12/30/030 19:47
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum RedisDelayQueueEnum {

    /**
     * 测试key
     */
    KEY_1("KEY_1","测试key", "key1DelayQueueHandleImpl");

    /**
     * 延迟队列 Redis Key
     */
    private String code;

    /**
     * 中文描述
     */
    private String name;

    /**
     * 延迟队列具体业务实现的 Bean
     * 可通过 Spring 的上下文获取
     */
    private String beanId;
}
