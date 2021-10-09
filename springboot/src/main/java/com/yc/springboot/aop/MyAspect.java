package com.yc.springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect //告诉Spring 这是一个切面
@Component  //告诉Spring容器需要管理该对象
/**
 * https://www.cnblogs.com/wangshen31/p/9379197.html
 */
public class MyAspect {

    //通过规则确定哪些方法是需要增强的

    @Pointcut("execution (public * com.yc.springboot.controller..*.*(..))")
    public void controller(){};

    @Before("controller()")
    public void before(JoinPoint joinPoint){
        System.out.println("before");
    }

    @After("controller()")
    public void after(JoinPoint joinPoint) {
        System.out.println("after");
    }

}
