package com.yc.mybatisdemo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AccountServiceImpl extends ServiceImpl<MyAccountMapper, MyAccount> implements AccountService{

    @Resource
    private MyAccountMapper myAccountMapper;

    public MyAccount getAccountByUrid(String urid) {
         return myAccountMapper.selectByPrimaryKey(urid);
    }

    /**
     * 通过QueryWrapper查询数据
     * @param urid
     * @return
     */
    public MyAccount getByLambdaQueryWrapper(String urid) {
        LambdaQueryWrapper<MyAccount> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(MyAccount::getId, urid);
        return this.getOne(lambdaQueryWrapper);
    }

    @Override
    public MyAccount getSpecificFidld(String urid) {
        LambdaQueryWrapper<MyAccount> lambdaQueryWrapper = Wrappers.lambdaQuery();
        //使用select选择字段如果加多次，以最后一次为准
        lambdaQueryWrapper.select(MyAccount::getAccount, MyAccount::getId);
        lambdaQueryWrapper.eq(MyAccount::getId, urid);
        return this.getOne(lambdaQueryWrapper);
    }

    /**
     * 通过UpdateWrapper更新数据
     * @param name
     * @return
     */
    public boolean updateByLambdaUpdateWrapper(String urid, String name) {
        LambdaUpdateWrapper<MyAccount> lambdaQueryWrapper = Wrappers.lambdaUpdate();
        lambdaQueryWrapper.eq(MyAccount::getId, urid);
        lambdaQueryWrapper.set(MyAccount::getMerchantName, name);
        return this.update(lambdaQueryWrapper);
    }
}
