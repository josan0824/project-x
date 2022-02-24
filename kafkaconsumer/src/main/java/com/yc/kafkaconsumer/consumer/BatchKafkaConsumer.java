package com.yc.kafkaconsumer.consumer;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.yc.kafkaconsumer.utils.LogHelper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author: josan_tang
 * @create_date: 2022/1/5 15:50
 * @desc: Kafka批量消费者
 * @version:
 */
@Component
public class BatchKafkaConsumer {
    //线程使用数量
    public int executorNumber;

    public int executorNumberTemporary;

    @Resource(name = "dyCallBackMonitorExecutorTemporary")
    private ThreadPoolTaskExecutor dyCallBackMonitorExecutorTemporary;

    @KafkaListener(topics = "batchTopic", groupId = "myGroup")
    public void callback(List<ConsumerRecord<?, ?>> recordList, Acknowledgment ack) {
        if (CollUtil.isNotEmpty(recordList)) {
            LogHelper.writeInfoLog(this.getClass().getSimpleName(), "callback", "recordList:" + recordList.size());

            List<Future> futureList = new ArrayList<>();

            //使用线程池处理
            recordList.forEach(record -> {
                Optional<?> message = Optional.ofNullable(record.value());
                String jsonStr = message.get().toString();

                Future<?> future = dyCallBackMonitorExecutorTemporary.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            LogHelper.writeErrLog(Thread.currentThread().getName() + "-" + this.getClass().getSimpleName(), "callback", "jsonStr:" + jsonStr);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                futureList.add(future);
            });

            //等待结果
            for (Future future : futureList) {
                try {
                    future.get();
                    LogHelper.writeErrLog(Thread.currentThread().getName() + "-" + this.getClass().getSimpleName(), "callback", "future is done");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            LogHelper.writeErrLog(Thread.currentThread().getName() + "-" + this.getClass().getSimpleName(), "callback", "acknowledge");
            //确认提交
            ack.acknowledge();
        }
    }
}
