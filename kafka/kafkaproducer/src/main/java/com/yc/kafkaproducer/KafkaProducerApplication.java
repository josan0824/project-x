package com.yc.kafkaproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = "com.yc.kafkaproducer")
@EnableDiscoveryClient
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class KafkaProducerApplication {

    private static final String CLASS_NAME = "KafkaProducerApplication";
    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

}
