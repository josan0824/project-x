package com.yc.springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Aspect //告诉Spring 这是一个切面
@Component  //告诉Spring容器需要管理该对象
/**
 * https://www.cnblogs.com/wangshen31/p/9379197.html
 */
public class MyAspect {

    //通过规则确定哪些方法是需要增强的
    @Pointcut("execution (public * com.yc.springboot.controller.MyController.*())")
    public void controller() {
    }

    //前置通知
    @Before("controller()")
    public void before(JoinPoint joinPoint) {
        System.out.println("before advice");
    }

    //返回通知
    @AfterReturning(
            pointcut = "controller()",
            returning = "retVal"
    )
    public void afterReturning(Object retVal) {
        System.out.println("after returning advice, 返回结果 retVal:" + retVal);
    }

    //异常通知
    @AfterThrowing(
            pointcut = "controller()",
            throwing = "ex"
    )
    public void afterThrowing(Exception ex) {
        System.out.println("after throwing advice, 异常 ex:" + ex.getMessage());
    }

    //后置通知
    @After("controller()")
    public void after(JoinPoint joinPoint) {
        System.out.println("after advice");
    }

    //环绕通知
    @Around("controller()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("before advice");
        //相当于是before advice
        Object reVal = null;
        try {
            reVal = joinPoint.proceed();
        } catch (Exception e) {
            //相当于afterthrowing advice
            System.out.println("afterthrowing advice");
        }
        //相当于是after advice
        System.out.println("after advice");
        return reVal;
    }
}
