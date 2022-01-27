package com.yc.redis;

import com.yc.redis.utils.LogHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: josan_tang
 * @create_date: 2022/1/26 20:51
 * @desc:
 * @version:
 */
@SpringBootApplication(scanBasePackages = "com.yc.redis")
public class RedisApplication {
    private static final String CLASS_NAME = "RedisApplication";
    public static void main(String[] args) {
        LogHelper.writeInfoLog(CLASS_NAME, "main", "开始启动");
        SpringApplication.run(RedisApplication.class, args);

        LogHelper.writeInfoLog(CLASS_NAME, "main", "启动成功");
    }
}
