package com.yc.core.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**************************************************
 * copyright (c) 2022
 * created by josan tang
 * date:       
 * description: Environment相关方法
 **************************************************/
@RequestMapping("env")
@Api(tags = "测试Environment")
@RestController
public class EnvironmentController {

    @Autowired
    Environment env;

    @GetMapping("info")
    public String getEnv() {
        // 可以获取到spring.profiles.active
        String[] activeProfiles = env.getActiveProfiles();
        System.out.println("activeProfiles:" + String.join(" ", activeProfiles));
        String[] defaultProfiles = env.getDefaultProfiles();
        System.out.println("defaultProfiles:" + String.join(" ", defaultProfiles));
        return env.toString();
    }

}
