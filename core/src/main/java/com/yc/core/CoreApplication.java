package com.yc.core;

import com.yc.core.utils.LogHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.yc.core")
@EnableDiscoveryClient
public class CoreApplication {

    private static final String CLASS_NAME = "CoreApplication";
    public static void main(String[] args) {
        LogHelper.writeInfoLog(CLASS_NAME, "main", "开始启动");
        SpringApplication.run(CoreApplication.class, args);
        LogHelper.writeInfoLog(CLASS_NAME, "main", "启动成功");
    }

}
