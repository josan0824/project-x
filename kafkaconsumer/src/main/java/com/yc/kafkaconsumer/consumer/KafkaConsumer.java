package com.yc.kafkaconsumer.consumer;

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
import java.util.Optional;

/**
 * @author: josan_tang
 * @create_date: 2022/1/5 15:50
 * @desc: Kafka消费者
 * @version:
 */
@Component
public class KafkaConsumer {
    //线程使用数量
    public  int executorNumber;

    public  int executorNumberTemporary;

    @Resource(name = "dyCallBackMonitorExecutorTemporary")
    private ThreadPoolTaskExecutor dyCallBackMonitorExecutorTemporary;

/*    @KafkaListener(topics = "test2", groupId = "myGroup")
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
    }*/


    @KafkaListener(topics = "test2",  groupId = "myGroup")
    public void temporary(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        //LogHelper.writeInfoLog(this.getClass().getSimpleName(), "temporary", "进入方法:" + executorNumberTemporary + "-" + JSONObject.toJSONString(record.value()));
        while (executorNumberTemporary >= 20){
            LogHelper.writeInfoLog(this.getClass().getSimpleName(), "temporary", "需要等待executorNumberTemporary1:" + executorNumberTemporary + "-" + JSONObject.toJSONString(record.value()));
            if (executorNumberTemporary < 20){
                //LogHelper.writeInfoLog(this.getClass().getSimpleName(), "temporary", "executorNumberTemporary1:break" + + executorNumberTemporary + "-" + JSONObject.toJSONString(record.value()));
                break;
            }
        }
        //LogHelper.writeInfoLog(this.getClass().getSimpleName(), "temporary", "提交任务：" + executorNumberTemporary + "-" + JSONObject.toJSONString(record.value()));
        dyCallBackMonitorExecutorTemporary.execute(()->{
            synchronized(this) {
                ++executorNumberTemporary;
                if (executorNumberTemporary > 20) {
                    //LogHelper.writeInfoLog(this.getClass().getSimpleName(), "temporary", "执行前自增:executorNumberTemporary：" + executorNumberTemporary + "-" + JSONObject.toJSONString(record.value()));
                }

            }
            Optional<?> message = Optional.ofNullable(record.value());
            if (!message.isPresent()) {
                ack.acknowledge();
                return;
            }
            try {
                Thread.sleep(5000);
            } catch (Throwable e) {
                LogHelper.writeErrLog(this.getClass().getSimpleName(),Thread.currentThread() .getStackTrace()[1].getMethodName(),e);
            } finally {
                synchronized(this) {
                    --executorNumberTemporary;
                    //LogHelper.writeInfoLog(this.getClass().getSimpleName(), "temporary", "执行结束后自减:executorNumberTemporary：" + executorNumberTemporary + "-" + JSONObject.toJSONString(record.value()));
                    LogHelper.writeInfoLog(this.getClass().getSimpleName(), "temporary", JSONObject.toJSONString(record.value()));
                }
                // 回复kafka 消费成功
                ack.acknowledge();
            }
        });
    }
}
