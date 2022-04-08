package com.yc.kafkaproducer.sender;

import com.alibaba.fastjson.JSONObject;
import com.yc.kafkaproducer.utils.LogHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

/**
 * @author: josan_tang
 * @create_date: 2022/1/5 14:03
 * @desc: Kafka生产者
 * @version:
 */
@Component
public class KafkaSender {
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * kafka发送接口
     * @param topic
     * @param msg
     */
    public void send(String topic, Object msg) {
        String str = JSONObject.toJSONString(msg);
        ListenableFuture<SendResult<String, Object>> future =  kafkaTemplate.send(topic, str);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                LogHelper.writeErrLog(this.getClass().getSimpleName(), "send", "发送异常ex：" + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                LogHelper.writeInfoLog(this.getClass().getSimpleName(), "send", "发送成功");
            }
        });
    }


    /**
     * kafka发送接口同步
     * @param topic
     * @param msg
     */
    public void sendSynchronous(String topic, Object msg) {
        String str = JSONObject.toJSONString(msg);
        try {
            SendResult future =  kafkaTemplate.send(topic, str).get();
            LogHelper.writeInfoLog(this.getClass().getSimpleName(), "send", "发送成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
