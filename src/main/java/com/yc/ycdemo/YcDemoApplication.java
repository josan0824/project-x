package com.yc.ycdemo;

import com.yc.ycdemo.utils.LogHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.yc.ycdemo")
@EnableDiscoveryClient
public class YcDemoApplication {
    private static final String CLASS_NAME = "YcDemoApplication";
    public static void main(String[] args) {
        LogHelper.writeInfoLog(CLASS_NAME, "main", "开始启动");
        SpringApplication.run(YcDemoApplication.class, args);
        LogHelper.writeInfoLog(CLASS_NAME, "main", "启动成功");
    }

}
