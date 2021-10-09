package com.yc.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/my")
public class MyController {

    @GetMapping("/test")
    public void test(String content) {
        System.out.println("content:" + content);
    }
}
