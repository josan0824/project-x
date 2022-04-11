package com.yc.mybatisdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.mybatisdemo.model.MyAccount;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *@author: josan_tang
 *@create_date: 2022/4/8 14:34
 *@desc:
 *@version:
 */
    
public interface MyAccountMapper extends BaseMapper<MyAccount> {
    int deleteByPrimaryKey(String id);

    int insert(MyAccount record);

    int insertSelective(MyAccount record);

    MyAccount selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MyAccount record);

    int updateByPrimaryKey(MyAccount record);

    int updateBatch(List<MyAccount> list);

    int updateBatchSelective(List<MyAccount> list);

    int batchInsert(@Param("list") List<MyAccount> list);
}