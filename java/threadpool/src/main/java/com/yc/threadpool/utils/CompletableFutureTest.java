package com.yc.threadpool.utils;


import java.util.concurrent.*;
import java.util.function.BiConsumer;

/**************************************************
 * copyright (c) 2022
 * created by josan tang
 * date:  合并多个数据请求
 * description: 
 **************************************************/
public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4,
                100L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10));
        long start = System.currentTimeMillis();
        CompletableFuture<Void> all = null;
        String str1 = "str1";
        CompletableFuture<String> cf1 = getStr1(str1, executor);
        String str2 = "str2";
        CompletableFuture<String> cf2 = getStr2(str2, executor);
        all = CompletableFuture.allOf(cf1, cf2);
        System.out.println("start block");
        // 开始等待所有任务执行完成
        all.join();
        System.out.println("block finish, consume time:" + (System.currentTimeMillis() - start));
        System.out.println("cf1:" + cf1.get());
        System.out.println("cf2:" + cf2.get());
    }

    public static CompletableFuture<String> getStr1(String str,ThreadPoolExecutor executor) {
        // 定义任务
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return str + "-join1";
        }, executor);

        cf.whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String t, Throwable u) {
                System.out.println("hello " + t);
            }
        });
        return cf;
    }


    public static CompletableFuture<String> getStr2(String str,ThreadPoolExecutor executor) {
        // 定义任务
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return str + "-join2";
        }, executor);

        cf.whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String t, Throwable u) {
                System.out.println("hello " + t);
            }
        });
        return cf;
    }




}
