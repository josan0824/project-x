package com.yc.mybatisdemo.controller;

import com.yc.mybatisdemo.service.AccountService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    TransactionalController transactionalController;

    @Autowired
    AccountService accountService;

    @PostMapping(value = "test")
    public String test() {
        test2();
        return "成功";
    }

    @Transactional(rollbackFor = Exception.class)
    public void test2() {
        accountService.insert();
        this.test3();
    }

    private void test3() {
        throw  new RuntimeException("测试");
    }
}
