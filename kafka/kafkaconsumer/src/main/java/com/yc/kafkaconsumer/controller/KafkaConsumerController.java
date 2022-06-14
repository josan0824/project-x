package com.yc.kafkaconsumer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
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


    /**
     * 重新平衡时保存offset
     */
    public class SaveOffsetOnRebalance implements  ConsumerRebalanceListener {

        @Override
        public void onPartitionsRevoked(Collection<TopicPartition> collection) {
            //提交事务
            //在处理完记录之后，将记录和偏移量插入数据库，然后在即将逝去分区所有权之前提交事务，确保成功保存了这些信息
            //commitDBTransaction();
        }

        @Override
        public void onPartitionsAssigned(Collection<TopicPartition> collection) {
            for (TopicPartition partition : collection) {
                //从特定的offset开始消费
                //从数据库获取偏移量，在分配到新分区的时候，使用seek方法定位到那些记录
                //kafkaConsumer.seek(partition, getOffsetFromDB(partition));
            }
        }
    }

    @ApiModelProperty("从特定偏移量处开始处理消息")
    @GetMapping("handleMessageWithPosition")
    public String handleMessageWithPosition() {
        //创建kafkaconsumer
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "127.0.0.1:9092");
        kafkaProperties.put("group.id", "mygroup");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaConsumer = new KafkaConsumer<String, String>(kafkaProperties);

        //订阅
        kafkaConsumer.subscribe(Collections.singletonList("mytopic"), new SaveOffsetOnRebalance());
        kafkaConsumer.poll(0);

        for(TopicPartition partition : kafkaConsumer.assignment()) {
            //从特定的offset消费
            //订阅主题之后，开始启动消费者，我们调用一次poll方法，让消费者加入到消费者群组里，并获取到分
            //的分区，然后马上调用seek方法定位分区的偏移量。要记住，seek方法只更新我们正在使用的位置，在下次调用
            //poll时就可以获得正确的消息。
            //kafkaConsumer.seek(partition, getOffsetFromDB(partition));
        }

        int count = 1;
        //轮询消息
        try {
            //死循环
            while(true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    //rocessRecord(record);
                    //storeRecordInDB(record);
                    //这次要更新的是数据库用于保存偏移量的表。假设更新记录的速度非常快，所以每条记录都需要更新一次数据库
                    //但提交的速度比较慢，所以只在每个批次末尾提交一次。
                    //storeOffsetInDB(record.topic(), record.partition(), record.offset());
                }
                //commitDBTransaction();
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


    @ApiModelProperty("分配分区")
    @GetMapping("distributionPartition")
    public String distributionPartition() {
        //创建kafkaconsumer
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "127.0.0.1:9092");
        kafkaProperties.put("group.id", "mygroup");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaConsumer = new KafkaConsumer<String, String>(kafkaProperties);

        List<PartitionInfo> partitionInfos = null;
        List<TopicPartition> partitions = new ArrayList<>();
        //向集群请求主体可用的分区，如果只打算读取特定分区，可以跳过这一步
        partitionInfos = kafkaConsumer.partitionsFor("topicName");

        if (partitionInfos != null) {
            for (PartitionInfo partitionInfo : partitionInfos) {
                partitions.add(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()));
            }
            //知道哪些分区后，调用assign
            kafkaConsumer.assign(partitions);
            //轮询消息
            try {
                //死循环
                while(true) {
                    ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                    for (ConsumerRecord<String, String> record : records) {
                       //处理消息
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
        }

        return "ok";
    }


    @ApiModelProperty("退出")
    @GetMapping("exit")
    public String exit() {
        //创建kafkaconsumer
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "127.0.0.1:9092");
        kafkaProperties.put("group.id", "mygroup");
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaConsumer = new KafkaConsumer<String, String>(kafkaProperties);

        //订阅
        kafkaConsumer.subscribe(Collections.singletonList("mytopic"));
        Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run() {
                System.out.println("start exit...");
                kafkaConsumer.wakeup();
                try {
                    mainThread.join();
                } catch (Exception e){
                    System.out.println("Exception exit...");
                }
            }
        });

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
