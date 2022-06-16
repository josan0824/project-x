package com.yc.javabasic.concurrent;

/**
 * 原子性测试
 */
public class VisibilityTest {
    public static void main(String[] args) throws InterruptedException {
        //两个线程对同一个值，每个线程自增1w，最后结果不是2w
        System.out.println(calc());
    }

    private static long count = 0;
    private void add10K() {
        int idx = 0;
        while(idx++ < 10000) {
            count += 1;
        }
    }
    public static long calc() throws InterruptedException {
        final VisibilityTest test = new VisibilityTest();
        // 创建两个线程，执行add()操作
        Thread th1 = new Thread(()->{
            test.add10K();
        });
        Thread th2 = new Thread(()->{
            test.add10K();
        });
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        return count;
    }
}

