package com.yc.mybatisdemo.service;

import com.yc.mybatisdemo.mapper.MyAccountMapper;
import com.yc.mybatisdemo.model.MyAccount;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: josan_tang
 * @create_date: 2022/4/8 15:26
 * @desc:
 * @version:
 */
@Service
public class AccountService {

    @Resource
    private MyAccountMapper myAccountMapper;

    public MyAccount getAccountByUrid(String urid) {
         return myAccountMapper.selectByPrimaryKey(urid);
    }
}
