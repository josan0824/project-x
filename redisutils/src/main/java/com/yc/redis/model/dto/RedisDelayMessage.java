package com.yc.redis.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description 延迟队列消息对象实体
 * 用于存放需要加入延迟队列的数据
 * @author: josan_tang
 * @createDate: 2021/12/30 21:42
 */
@Data
public class RedisDelayMessage implements Serializable {
    private static final long serialVersionUID = 7986323164501592595L;

    /**
     * 自定义编码
     */
    private int code;

    /**
     * 自定义消息
     */
    private String message;

    /**
     * 业务数据
     */
    private Object data;

    /**
     * 过期时间
     */
    private String delayDate;

    /**
     * 任务主键
     */
    private String taskId;
}
