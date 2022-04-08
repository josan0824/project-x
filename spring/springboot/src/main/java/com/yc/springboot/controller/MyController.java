package com.yc.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
public class MyController {

    @GetMapping("/test")
    public void test() {
        System.out.println("test 业务方法");
    }

    @GetMapping("/testBefore")
    public void testBefore() {
        System.out.println("testBefore 业务方法");
    }

    @GetMapping("/testAfterReturning")
    public String testAfterReturning() {
        System.out.println("testAfterReturning 业务方法");
        return "我是一个返回值";
    }

    @GetMapping("/testAfterThrowing")
    public void testAfterThrowing() {
        System.out.println("testAfterThrowing 业务方法");
        int a = 0;
        int b = 1 / a;
    }

    @GetMapping("/testAfter")
    public void testAfter() {
        System.out.println("testAfter 业务方法");
    }

    @GetMapping("/around")
    public void around() {
        System.out.println("around 业务方法");
    }
}
