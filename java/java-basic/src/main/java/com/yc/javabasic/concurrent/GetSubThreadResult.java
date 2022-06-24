package com.yc.javabasic.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: josan_tang
 * @create_date: 2022/6/24 14:35
 * @desc: 得到子线程的结果
 * @version:
 */
public class GetSubThreadResult {

    /**
     * 线程池
     */
    static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        useFutureTask();
    }


    /**
     * 使用FutrueTask得到线程池的返回结果
     * @throws InterruptedException
     */
    private static void useFutureTask() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        List<FutureTask> futureTaskList = new ArrayList<>();
        FutureTask<String> futureTask1 = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "String 1";
            }
        });
        executorService.execute(futureTask1);

        FutureTask<String>  futureTask2 = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "String 2";
            }
        });
        executorService.execute(futureTask2);
        futureTaskList.add(futureTask1);
        futureTaskList.add(futureTask2);
        List<String> result = new ArrayList<>();
        for (FutureTask futureTask: futureTaskList) {
            result.add((String) futureTask.get());
        }
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + ":result:" + result + ",耗时：" +(end - start) / 1000 + "s");
    }
}
