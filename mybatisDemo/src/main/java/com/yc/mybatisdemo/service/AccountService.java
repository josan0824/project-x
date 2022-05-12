package com.yc.mybatisdemo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.mybatisdemo.domain.PageAccountDTO;
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
public interface AccountService extends IService<MyAccount> {

    MyAccount getAccountByUrid(String urid);

    /**
     * 通过QueryWrapper查询数据
     * @param urid
     * @return
     */
    MyAccount getByLambdaQueryWrapper(String urid);

    /**
     * 通过QueryWrapper查询数据
     * @param urid
     * @return
     */
    MyAccount getSpecificFidld(String urid);

    /**
     * 通过UpdateWrapper更新数据
     * @param name
     * @return
     */
    boolean updateByLambdaUpdateWrapper(String urid, String name);

    /**
     * 测试条件构造器
     */
    void testWrapper();

    /**
     * 根据页面查询
     */
    Page<MyAccount> selectPage(PageAccountDTO dto);

    void testSimpleMappper();
}
