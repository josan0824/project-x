package com.yc.mybatisdemo.controller;

import com.yc.mybatisdemo.model.MyAccount;
import com.yc.mybatisdemo.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: josan_tang
 * @create_date: 2022/4/8 14:05
 * @desc:
 * @version:
 */
@RestController
@RequestMapping("/test")
public class AccountController {

    @Resource
    private AccountService accountService;

    @GetMapping("/getByUrid")
    @ApiOperation(value = "通过用户id得到用户")
    public MyAccount getByUrid(String urid) {
        return accountService.getAccountByUrid(urid);
    }
}
