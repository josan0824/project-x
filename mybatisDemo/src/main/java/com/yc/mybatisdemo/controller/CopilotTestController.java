package com.yc.mybatisdemo.controller;

import com.yc.mybatisdemo.domain.LeeJSONResult;
import com.yc.mybatisdemo.domain.ValidatedTestBO;
import com.yc.mybatisdemo.model.MyAccount;
import com.yc.mybatisdemo.model.MyAccountVO;
import com.yc.mybatisdemo.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**************************************************
 * copyright (c) 2022
 * created by josan tang
 * date: 2023-06-08
 * description: Copilot测试Controller
 *
 * 优点：
 * 1.模仿已存在的代码
 * 2.stream的使用
 * 3.swagger、validation的使用
 * 4.mybatis-plus的使用
 * 5.注释生成代码
 *
 * 缺点：
 * 1.生成后格式化问题
 *
 * 注意项：
 * 1.自己review最终的代码
 *
 **************************************************/
@RestController
@RequestMapping("/copilot")
public class CopilotTestController {
    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        List<String> result2 = null;
        result.addAll(result2);
    }


    @Autowired
    private AccountService accountService;

    /**
     * 模仿
     * @param list
     */
    private List<MyAccountVO> imitation(List<MyAccount> list) {
        return list.stream().map(account -> {
            MyAccountVO myAccountVO = new MyAccountVO();
            myAccountVO.setId(account.getId());
            myAccountVO.setAccount(account.getAccount());
            myAccountVO.setCompany(account.getCompany());
            myAccountVO.setUrid(account.getUrid());



            return myAccountVO;
        }).collect(Collectors.toList());
    }

    /**
     * 根据公司分组
     * @param accountList
     * @return
     */
    private Map<String, List<MyAccount>> groupByCompany(List<MyAccount> accountList) {
        if (accountList == null || accountList.size() == 0) {
            return null;
        }
        Map<String, List<MyAccount>> companyMap = accountList.stream().collect(Collectors.groupingBy(MyAccount::getCompany));
        return companyMap;
    }

    @PostMapping("/testPostValidated")
    @ApiOperation(value = "post-Validated参数校验测试")
    public LeeJSONResult testValidated(@Validated @RequestBody ValidatedTestBO validatedTestBO) {
        return LeeJSONResult.ok();
    }

    @GetMapping("/getByUrid")
    @ApiOperation(value = "通过用户id得到用户")
    public MyAccount getByUrid(String urid) {
        return accountService.getAccountByUrid(urid);
    }

    /**
     * 两个int类型数之和
     */
    @GetMapping("/getSum")
    @ApiOperation(value = "两个int类型数之和")
    public int getSum(int a, int b) {
        return a + b;
    }

}
