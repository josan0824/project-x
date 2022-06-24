package com.yc.javabasic.concurrent;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: josan_tang
 * @create_date: 2022/6/17 10:52
 * @desc: 线程本地存储
 * @version:
 */
public class ThreadLocalTest {
    static final AtomicLong
            nextId=new AtomicLong(0);
    //定义ThreadLocal变量
    static final ThreadLocal<Long> tl = ThreadLocal.withInitial(()->nextId.getAndIncrement());



    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":" + get());
                for (int i = 0; i < 20; i++) {
                    final ThreadLocal<Long> tl2 = new ThreadLocal<>();
                    tl2.set(1L);
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":" + get());
            }
        });
        t2.start();

    }
    //此方法会为每个线程分配一个唯一的Id
    static long get(){
        return tl.get();
    }

}
