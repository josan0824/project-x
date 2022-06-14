package com.yc.mybatisdemo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.mybatisdemo.domain.PageAccountDTO;
import com.yc.mybatisdemo.model.MyAccount;
import com.yc.mybatisdemo.service.AccountService;
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
 *  https://baomidou.com/pages/10c804/#abstractwrapper
 */
@RestController
@RequestMapping("/test")
@Api(tags = "测试MyBatis plus")
public class AccountController {

    @Resource
    private AccountService accountServiceImpl;

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

    @GetMapping("/getSpecificFidld")
    @ApiOperation(value = "查询特定的字段")
    public MyAccount getSpecificFidld(String urid) {
        return accountServiceImpl.getSpecificFidld(urid);
    }

    @GetMapping("/updateByLambdaUpdateWrapper")
    @ApiOperation(value = "通过UpdateWrapper修改数据")
    public boolean updateByLambdaUpdateWrapper(String urid, String name) {
        return accountServiceImpl.updateByLambdaUpdateWrapper(urid, name);
    }

    @GetMapping("/testWrapper")
    @ApiOperation(value = "测试各种查询条件")
    public String testWrapper() {
        accountServiceImpl.testWrapper();
        return "查看打印日志";
    }

    @GetMapping("/selectPage")
    @ApiOperation(value = "分页查询用户信息")
    public Page<MyAccount> selectPage(PageAccountDTO dto) {
        return accountServiceImpl.selectPage(dto);
    }

    @GetMapping("/testSimpleMappper")
    @ApiOperation(value = "测试简单的mapper")
    public String testSimpleMappper() {
        accountServiceImpl.testSimpleMappper();
        return "ok";
    }

    @GetMapping("/insert")
    @ApiOperation(value = "insert")
    public String insert() {
        accountServiceImpl.insert();
        return "ok";
    }


    @GetMapping("/distinct")
    @ApiOperation(value = "distinct")
    public String distinct() {
        accountServiceImpl.distinct();
        return "ok";
    }
}
