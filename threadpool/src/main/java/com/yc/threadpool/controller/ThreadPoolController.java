package com.yc.threadpool.controller;

import com.yc.threadpool.config.ThreadPoolConfig;
import com.yc.threadpool.utils.LogHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("threadpool")
@Api(tags = "线程池")
public class ThreadPoolController {

    @Resource(name = "testThreadPool")
    private ThreadPoolTaskExecutor testThreadPool;

    @GetMapping(value = "submitTask")
    @ApiOperation(value="submitTask", notes ="提交任务")
    public String submitTask(int taskNum) {
        for (int i = 0 ; i < taskNum; i ++) {
            int finalI = i;
            testThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    LogHelper.writeInfoLog("ThreadPoolController", "submitTask", "提交任务之前:" + finalI);
                    try {
                        Thread.sleep(8000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LogHelper.writeInfoLog("ThreadPoolController", "submitTask", "提交任务之后:" + finalI);
                }
            });
        }

/*        int corePoolSize = testThreadPool.getCorePoolSize();
        while (corePoolSize > 1) {
            //核心线程数
            corePoolSize = testThreadPool.getCorePoolSize();
            //当前线程池数据
            int poolSize = testThreadPool.getPoolSize();
            //当前活动的线程
            int activeCount = testThreadPool.getActiveCount();
            //线程池最大线程
            int maxPoolSize = testThreadPool.getMaxPoolSize();
            //数组大小
            int queueSize = testThreadPool.getThreadPoolExecutor().getQueue().size();
            LogHelper.writeInfoLog("ThreadPoolController", "getThreadPoolInfo",
                    "corePoolSize:" + corePoolSize + ";" +
                            "poolSize:" + poolSize + ";" +
                            "activeCount:" + activeCount + ";" +
                            "maxPoolSize:" + maxPoolSize + ";" +
                            "queueSize:" + queueSize);
        }*/

        return "ok";
    }


    @GetMapping(value = "getThreadPoolInfo")
    @ApiOperation(value="getThreadPoolInfo", notes ="得到线程池信息")
    public String getThreadPoolInfo() {
        //核心线程数（配置）
        int corePoolSize = testThreadPool.getCorePoolSize();
        //当前线程池数据，未被回收的
        int poolSize = testThreadPool.getPoolSize();
        //当前活动的线程，未被回收且在执行任务的
        int activeCount = testThreadPool.getActiveCount();
        //线程池最大线程（配置）
        int maxPoolSize = testThreadPool.getMaxPoolSize();
        //数组大小
        int queueSize = testThreadPool.getThreadPoolExecutor().getQueue().size();
        LogHelper.writeInfoLog("ThreadPoolController", "getThreadPoolInfo",
                "corePoolSize:" + corePoolSize + ";" +
                        "poolSize:" + poolSize + ";" +
                        "activeCount:" + activeCount + ";" +
                        "maxPoolSize:" + maxPoolSize + ";" +
                        "queueSize:" + queueSize);
        return "ok";
    }
}
