package com.yc.core.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("testnacos")
@Api(tags = "测试Nacos")
//自动刷新
@RefreshScope
public class TestNacosController {
    @Value("${test.name}")
    private String name;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test() {
        return "name:" + name;
    }
}
