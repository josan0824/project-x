package com.yc.springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect //告诉Spring 这是一个切面
@Component  //告诉Spring容器需要管理该对象
public class MyAspect implements Ordered {

    //通过规则确定哪些方法是需要增强的
    @Pointcut("execution (public * com.yc.springboot.controller.MyController.*())")
    public void controller() {
    }

/*    //前置增强
    @Before("controller()")
    public void before1(JoinPoint joinPoint) {
        System.out.println("before1 advice");
    }*/

    //前置增强
    @Before("controller()")
    public void before2(JoinPoint joinPoint) {
        System.out.println("before2 advice");
    }

    //返回增强
    @AfterReturning(
            pointcut = "controller()",
            returning = "retVal"
    )
    public void afterReturning(JoinPoint joinPoint, Object retVal) {
        System.out.println("after returning advice, 返回结果 retVal:" + retVal);
    }

    //异常增强
    @AfterThrowing(
            pointcut = "controller()",
            throwing = "ex"
    )
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        System.out.println("after throwing advice, 异常 ex:" + ex.getMessage());
    }

    //后置增强
    @After("controller()")
    public void after(JoinPoint joinPoint) {
        System.out.println("after advice");
    }

    //环绕增强
    @Around("controller()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before advice");
        //相当于是before advice
        Object reVal = null;
        try {
            reVal = joinPoint.proceed();
        } catch (Exception e) {
            //相当于afterthrowing advice
            System.out.println("around afterthrowing advice");
        }
        //相当于是after advice
        System.out.println("around after advice");
        return reVal;
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
