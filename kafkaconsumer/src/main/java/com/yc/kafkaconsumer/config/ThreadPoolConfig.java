package com.yc.kafkaconsumer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author: create by xuqichen
 * @version: v1.1
 * @description: com.live.config
 * @date:2021/6/8
 */
@Slf4j
@Configuration
public class ThreadPoolConfig {

    private static final int CORE_POOL_SIZE = 50;

    private static final int MAX_POOL_SIZE = 200;

    private static final int QUEUE_SIZE = 100;

    private static final int KEEP_ALIVE = 200;

	@Bean(name = "dyCallBackMonitorExecutorTemporary")
	public ThreadPoolTaskExecutor dyCallBackMonitorExecutorTemporary() {
		return newThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE, QUEUE_SIZE, "dyCallBackMonitorExecutorTemporary-");
	}

	private ThreadPoolTaskExecutor newThreadPoolExecutor(int corePoolSize, int maxPoolSize, int keepAliveSeconds,
        int queueSize, String prefixThreadName) {

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueSize);
		executor.setKeepAliveSeconds(keepAliveSeconds);
		executor.setThreadNamePrefix(prefixThreadName);
		// 默认AbortPolicy策略
		executor.initialize();
		return executor;
	}
}
