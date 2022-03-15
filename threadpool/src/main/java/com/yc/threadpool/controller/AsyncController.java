package com.yc.threadpool.controller;

import com.yc.threadpool.service.AsyncServiceImpl;
import com.yc.threadpool.utils.LogHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("测试异步")
@Api(tags = "测试异步")
public class AsyncController {

    @Resource
    private AsyncServiceImpl asyncService;

    @GetMapping(value = "testAsync")
    @ApiOperation(value="testAsync", notes ="测试异步")
    public String testAsync() {
        System.out.println("当前线程：" + Thread.currentThread().getName() + ",线程id:" + Thread.currentThread().getId());
        asyncService.testAsync();
        return "ok";
    }
}
