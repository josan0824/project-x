package com.yc.kafkaconsumer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
                //同步提交
                try {
                    kafkaConsumer.commitSync();
                } catch (Exception e) {
                    System.out.println("commit fialed");
                }
                //异步提交
                kafkaConsumer.commitAsync();
                //异步提交处理提交的回调信息
                kafkaConsumer.commitAsync(new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
                        if (e != null) {
                            System.out.println("commit fialed for offsets{}");
                        }
                    }
                });
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            kafkaConsumer.close();
        }
        return "ok";
    }


    @ApiModelProperty("同步提交和异步提交")
    @GetMapping("commitSyncAndAsync")
    public String commitSyncAndAsync() {
        //创建kafkaconsumer
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "127.0.0.1:9092");
        kafkaProperties.put("group.id", "mygroup");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(kafkaProperties);

        //订阅
        kafkaConsumer.subscribe(Collections.singletonList("mytopic"));

        //轮询消息
        try {
            //死循环
            while(true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("topic = " + record.topic() + ",partition = " + record.partition()
                            + ",offset:" + record.offset() + ",key:" + record.key() + ",value:" + record.value());

                }
                //异步提交
                kafkaConsumer.commitAsync();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            //同步提交
            try {
                kafkaConsumer.commitSync();
            } catch (Exception e) {
                System.out.println("commit fialed");
            } finally {
                kafkaConsumer.close();
            }
        }
        return "ok";
    }


    @ApiModelProperty("提交特定的偏移量")
    @GetMapping("commitOffsetByFeatrue")
    public String commitOffsetByFeatrue() {
        //创建kafkaconsumer
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "127.0.0.1:9092");
        kafkaProperties.put("group.id", "mygroup");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(kafkaProperties);

        //订阅
        kafkaConsumer.subscribe(Collections.singletonList("mytopic"));

        Map<TopicPartition, OffsetAndMetadata> currentOffset = new HashMap<>();
        int count = 1;
        //轮询消息
        try {
            //死循环
            while(true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("topic = " + record.topic() + ",partition = " + record.partition()
                            + ",offset:" + record.offset() + ",key:" + record.key() + ",value:" + record.value());
                    currentOffset.put(new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1, "no metadata"));
                    if (count % 100 == 0) {
                        kafkaConsumer.commitAsync(currentOffset, null);
                        count ++;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            //同步提交
            try {
                kafkaConsumer.commitSync();
            } catch (Exception e) {
                System.out.println("commit fialed");
            } finally {
                kafkaConsumer.close();
            }
        }
        return "ok";
    }

    KafkaConsumer<String, String> kafkaConsumer;
    Map<TopicPartition, OffsetAndMetadata> currentOffset = new HashMap<>();

    /**
     * 再均衡器实现类
     */
    private class HandleRebalance implements ConsumerRebalanceListener {

        @Override
        public void onPartitionsRevoked(Collection<TopicPartition> collection) {

        }

        @Override
        public void onPartitionsAssigned(Collection<TopicPartition> collection) {
            kafkaConsumer.commitSync(currentOffset);
        }
    }


    @ApiModelProperty("再均衡监听器")
    @GetMapping("rebalanceListener")
    public String rebalanceListener() {
        //创建kafkaconsumer
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "127.0.0.1:9092");
        kafkaProperties.put("group.id", "mygroup");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaConsumer = new KafkaConsumer<String, String>(kafkaProperties);

        //订阅
        kafkaConsumer.subscribe(Collections.singletonList("mytopic"), new HandleRebalance());


        int count = 1;
        //轮询消息
        try {
            //死循环
            while(true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("topic = " + record.topic() + ",partition = " + record.partition()
                            + ",offset:" + record.offset() + ",key:" + record.key() + ",value:" + record.value());
                    currentOffset.put(new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1, "no metadata"));
                }
                kafkaConsumer.commitAsync(currentOffset, null);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            //同步提交
            try {
                kafkaConsumer.commitSync();
            } catch (Exception e) {
                System.out.println("commit fialed");
            } finally {
                kafkaConsumer.close();
            }
        }
        return "ok";
    }

}
