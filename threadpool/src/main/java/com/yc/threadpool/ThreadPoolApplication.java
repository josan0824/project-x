package com.yc.threadpool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author: josan_tang
 * @create_date: 2022/2/7 16:34
 * @desc: 线程池测试项目
 * @version:
 */
@SpringBootApplication
@EnableAsync
public class ThreadPoolApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThreadPoolApplication.class, args);
    }
}
