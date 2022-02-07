package com.yc.kafkaproducer.controller;

import com.yc.kafkaproducer.annotation.Log;
import com.yc.kafkaproducer.sender.KafkaSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: josan_tang
 * @create_date: 2022/1/5 13:40
 * @desc:
 * @version:
 */
@RestController
@Api(tags = "测试")
@RequestMapping("/test")
public class TestController {

    @Autowired
    private KafkaSender kafkaSender;

    @Log
    @ApiModelProperty("发送消息")
    @GetMapping("sendMsg")
    public String sendMsg() {
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    kafkaSender.send("test2", "sendMsg:" + finalI);
                }
            }).start();
        }
        return "ok";
    }
}
