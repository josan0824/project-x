package com.yc.ycdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.yc.ycdemo")
public class YcDemoApplication {

    public static void main(String[] args) {
        System.out.println("启动了");
        SpringApplication.run(YcDemoApplication.class, args);
    }

}
