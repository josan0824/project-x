package com.yc.mybatisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: josan_tang
 * @create_date: 2022/4/8 14:03
 * @desc:
 * @version:
 */
@SpringBootApplication
@MapperScan({"com.yc.mybatisdemo.mapper"})
public class MybatisDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }
}
