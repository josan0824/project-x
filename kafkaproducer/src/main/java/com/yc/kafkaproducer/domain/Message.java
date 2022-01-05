package com.yc.kafkaproducer.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author: josan_tang
 * @create_date: 2022/1/5 14:03
 * @desc:
 * @version:
 */
@Data
public class Message {
    private Long id;

    private String msg;

    private Date sendTime;
}
