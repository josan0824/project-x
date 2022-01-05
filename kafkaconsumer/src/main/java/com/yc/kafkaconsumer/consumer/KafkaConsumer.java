package com.yc.kafkaconsumer.consumer;

import com.yc.kafkaconsumer.utils.LogHelper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author: josan_tang
 * @create_date: 2022/1/5 15:50
 * @desc: Kafka消费者
 * @version:
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test", groupId = "myGroup", properties = {"0"})
    public void callback(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<?> message = Optional.ofNullable(record.value());
        String jsonStr= message.get().toString();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogHelper.writeErrLog(Thread.currentThread().getName() + "-" +  this.getClass().getSimpleName(), "callback", "jsonStr:" + jsonStr);
        ack.acknowledge();
    }

    @KafkaListener(topics = "test", groupId = "myGroup", properties = {"1"})
    public void callback1(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<?> message = Optional.ofNullable(record.value());
        String jsonStr= message.get().toString();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogHelper.writeErrLog(Thread.currentThread().getName() + "-" +  this.getClass().getSimpleName(), "callback1", "jsonStr:" + jsonStr);
        ack.acknowledge();
    }
    @KafkaListener(topics = "test", groupId = "myGroup", properties = {"2"})
    public void callback2(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<?> message = Optional.ofNullable(record.value());
        String jsonStr= message.get().toString();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogHelper.writeErrLog(Thread.currentThread().getName() + "-" +  this.getClass().getSimpleName(), "callback2", "jsonStr:" + jsonStr);
        ack.acknowledge();
    }
}
