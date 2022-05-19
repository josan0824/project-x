package com.yc.kafkaproducer.controller;

import com.yc.kafkaproducer.annotation.Log;
import com.yc.kafkaproducer.sender.KafkaSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * @author: josan_tang
 * @create_date: 2022/1/5 13:40
 * @desc:
 * @version:
 */
@RestController
@Api(tags = "测试kafka")
@RequestMapping("/kafka")
public class KafkaProducerController {

    @Autowired
    private KafkaSender kafkaSender;

    String topic = "test3";

    @Log
    @ApiOperation("发送消息(简单地)")
    @GetMapping("sendMsgSimple")
    public String sendMsg(int number) {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "127.0.0.1:9092");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer kafkaProducer = new KafkaProducer<String, String>(kafkaProperties);

        //不关心返回发送消息
        ProducerRecord<String, String> record = new ProducerRecord<>(topic,"name","josan");
        try {
            kafkaProducer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @Log
    @ApiOperation("发送消息(同步发送)")
    @GetMapping("sendMsgSync")
    public String sendMsgSync() {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "127.0.0.1:9092");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer kafkaProducer = new KafkaProducer<String, String>(kafkaProperties);

        //同步发送消息
        ProducerRecord<String, String> record = new ProducerRecord<>(topic,"name","josan");
        try {
           RecordMetadata recordResult = (RecordMetadata) kafkaProducer.send(record).get();
           System.out.println("recordResult:" + recordResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @Log
    @ApiOperation("发送消息(异步发送)")
    @GetMapping("sendMsgAsync")
    public String sendMsgAsync() {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "127.0.0.1:9092");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer kafkaProducer = new KafkaProducer<String, String>(kafkaProperties);

        //异步发送消息
        ProducerRecord<String, String> record = new ProducerRecord<>(topic,"name","josan");
        try {
           kafkaProducer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println("recordMetadata:" + recordMetadata);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
