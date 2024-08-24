package com.yc.core.controller;

import com.yc.core.annotation.Log;
import io.swagger.annotations.Api;
import org.springframework.aop.framework.AopContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**************************************************
 * copyright (c) 2022
 * created by josan tang
 * date:       
 * description: 
 **************************************************/
@RestController
@RequestMapping("/transaction")
@Api(tags = "事务测试")
public class TransactionalController {
    @Log
    @PostMapping(value = "test")
    public String test() {

        return "a";
    }
}
