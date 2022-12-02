package com.yc.threadpool.utils;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CountDownLatch;

/**************************************************
 * copyright (c) 2022
 * created by josan tang
 * date: 2022-11-15
 * description: 测试核心线程数设置
 **************************************************/
public class TestCoreThreadNum {


    public static void main(String[] args) throws InterruptedException {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(100);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(100);
        executor.setKeepAliveSeconds(60);
        executor.initialize();

        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("i开始:" + finalI);
                        Thread.sleep(5000);
                        System.out.println("i结束:" + finalI);
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("cost: " + (end - start) + " ms");
    }

}
