package com.yc.threadpool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author: josan_tang
 * @create_date: 2022/2/7 16:36
 * @desc: 线程池
 * @version:
 * 如果任务数超过最大线程数+队列数，则会报如下错误：
 * Executor [java.util.concurrent.ThreadPoolExecutor@4594887f[Running, pool size = 20, active threads = 20, queued tasks = 10, completed tasks = 95]] did not accept task: com.yc.threadpool.controller.ThreadPoolController$1@47b60b92] with root cause
 * java.util.concurrent.RejectedExecutionException: Task java.util.concurrent.FutureTask@cd5e0a7 rejected from java.util.concurrent.ThreadPoolExecutor@4594887f[Running, pool size = 20, active threads = 20, queued tasks = 10, completed tasks = 95]
 * 	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2063)
 * 	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:830)
 * 	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1379)
 * 	at java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:112)
 */
@Configuration
public class ThreadPoolConfig {
    private static final int CORE_POOL_SIZE = 50;

    private static final int MAX_POOL_SIZE = 200;

    private static final int QUEUE_SIZE = 10;

    private static final int KEEP_ALIVE = 20;

    @Bean(name = "testThreadPool")
    public ThreadPoolTaskExecutor getTestThreadPool() {
        return newThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE , KEEP_ALIVE, QUEUE_SIZE, "testThreadPool");
    }

    /**
     * 初始化线程池
     * @param corePoolSize
     * @param maxPoolSize
     * @param keepAliveSeconds
     * @param queueSize
     * @param prefixThreadName
     * @return
     */
    private ThreadPoolTaskExecutor newThreadPoolExecutor(int corePoolSize,
                                                         int maxPoolSize,
                                                         int keepAliveSeconds,
                                                         int queueSize,
                                                         String prefixThreadName) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueSize);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix(prefixThreadName);
        //默认的拒绝策略为AbortPolicy
        executor.initialize();
        return executor;
    }
}
