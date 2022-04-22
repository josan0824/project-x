package com.yc.kafkaconsumer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Properties;

/**
 * @author: josan_tang
 * @create_date: 2022/1/5 13:40
 * @desc:
 * @version:
 */
@RestController
@Api(tags = "kafka消费者")
@RequestMapping("/kafkaconsumer")
public class KafkaConsumerController {


    @ApiModelProperty("发送消息(简单地)")
    @GetMapping("sendMsgSimple")
    public String sendMsg(int number) {
        //创建kafkaconsumer
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "127.0.0.1:9092");
        kafkaProperties.put("group.id", "mygroup");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(kafkaProperties);

        //订阅
        kafkaConsumer.subscribe(Collections.singletonList("mytopic"));
        //还可以通过正则匹配
        //kafkaConsumer.subscribe(Collections.singletonList("mytopic*"));

        //轮询消息
        try {
            //死循环
            while(true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("topic = " + record.topic() + ",partition = " + record.partition()
                    + ",offset:" + record.offset() + ",key:" + record.key() + ",value:" + record.value());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            kafkaConsumer.close();
        }

        return "ok";
    }

}
