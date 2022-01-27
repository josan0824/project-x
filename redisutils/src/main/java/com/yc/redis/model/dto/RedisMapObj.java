package com.yc.redis.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: josan_tang
 * @create_date: 2022/1/7 21:21
 * @desc:
 * @version:
 */
@Data
public class RedisMapObj implements Serializable {
    private static final long serialVersionUID = -1;

    private String name;

    private int age;
}
