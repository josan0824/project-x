package com.yc.springframework.di;

import com.yc.springframework.DITest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        //1. 读取配置文件实例化一个IOC容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/yc/springframework/di/spring-config.xml");
        //2. 从容器中获取bean
        UserService userService = applicationContext.getBean("userService", UserService.class);
    }
}
