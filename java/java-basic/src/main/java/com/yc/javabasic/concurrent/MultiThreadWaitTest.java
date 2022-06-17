package com.yc.javabasic.concurrent;

import lombok.SneakyThrows;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;

/**
 * @author: josan_tang
 * @create_date: 2022/6/17 9:25
 * @desc: 多线程等待测试
 * @version:
 */
public class MultiThreadWaitTest {

    /**
     * 线程池
     */
    static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws Exception {
        //new Thread + join方式
        //newThreadTest();
        //线程池 + CountDownLatch方式
        //countDownLatchTest();
        //线程池 + CyclicBarrier方式
        //cyclicBarrierTest();
        //线程池 + future.get
        futureGetTest();
    }

    /**
     * 通过new thread的方式创建的线程去进行等待
     * @throws InterruptedException
     */
    private static void newThreadTest() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ":newThreadTest开始进入");
        Thread t1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                cal();
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                cal2();
            }
        });
        t2.start();
        System.out.println(Thread.currentThread().getName() + ":start之后");
        //等待t1完成
        t1.join();
        System.out.println(Thread.currentThread().getName() + ":t1线程join之后");
        //等待t2完成
        t2.join();
        System.out.println(Thread.currentThread().getName() + ":t2线程join之后");
    }

    /**
     * 使用CountDownLatch解决线程池同步问题
     * @throws InterruptedException
     */
    private static void countDownLatchTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        executorService.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                cal();
                countDownLatch.countDown();
            }
        });
        executorService.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                cal2();
                countDownLatch.countDown();
            }
        });
        System.out.println(Thread.currentThread().getName() + ":提交之后");
        //等待执行完
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + ":执行完了");

    }

    /**
     * 使用CyclicBarrier来解决线程之间相互等待的过程
     * @throws BrokenBarrierException
     * @throws InterruptedException
     */
    private static void cyclicBarrierTest() throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            System.out.println(Thread.currentThread().getName() + ":放行");
        });

        executorService.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                cal();
                //等待执行完
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + ":t1执行完");
            }
        });
        executorService.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                cal2();
                //等待执行完
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + ":t2执行完");
            }
        });
        System.out.println(Thread.currentThread().getName() + ":执行完了");
    }

    /**
     * 通过future.get完成线程合并
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void futureGetTest() throws ExecutionException, InterruptedException {
        Future future1 = executorService.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                cal();
            }
        });
        Future future2 = executorService.submit(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                cal2();
            }
        });
        Object obj1 = future1.get();
        Object obj2 = future2.get();
        System.out.println(Thread.currentThread().getName() + ":执行完了");
    }

    /**
     * 模拟耗时操作1
     * @throws InterruptedException
     */
    private static void cal() throws InterruptedException {
        for (int i = 0; i < 5; i ++) {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

    /**
     * 模拟耗时操作2
     * @throws InterruptedException
     */
    private static void cal2() throws InterruptedException {
        for (int i = 0; i < 5; i ++) {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
