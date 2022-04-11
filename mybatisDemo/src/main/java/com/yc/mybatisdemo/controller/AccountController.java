package com.yc.mybatisdemo.controller;

import com.yc.mybatisdemo.model.MyAccount;
import com.yc.mybatisdemo.service.AccountServiceImpl;
import io.swagger.annotations.Api;
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
@Api(tags = "测试MyBatis plus")
public class AccountController {

    @Resource
    private AccountServiceImpl accountServiceImpl;

    @GetMapping("/getByUrid")
    @ApiOperation(value = "通过用户id得到用户")
    public MyAccount getByUrid(String urid) {
        return accountServiceImpl.getAccountByUrid(urid);
    }

    @GetMapping("/getByQueryWrapper")
    @ApiOperation(value = "通过QueryWrapper查询数据")
    public MyAccount getByQueryWrapper(String urid) {
        return accountServiceImpl.getByLambdaQueryWrapper(urid);
    }

    @GetMapping("/updateByLambdaUpdateWrapper")
    @ApiOperation(value = "通过UpdateWrapper修改数据")
    public boolean updateByLambdaUpdateWrapper(String urid, String name) {
        return accountServiceImpl.updateByLambdaUpdateWrapper(urid, name);
    }

}
