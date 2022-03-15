package com.yc.threadpool.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author: josan_tang
 * @create_date: 2022/3/15 16:34
 * @desc:
 * @version:
 */
@Service
public class AsyncServiceImpl {

    @Async
    public void testAsync() {
        System.out.println("当前线程：" + Thread.currentThread().getName() + ",线程id:" + Thread.currentThread().getId());
    }
}
