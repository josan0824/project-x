package com.yc.javabasic.collection;

import cn.hutool.core.collection.CollUtil;
import com.yc.javabasic.bean.Student;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: josan_tang
 * @create_date: 2022/3/26 15:12
 * @desc:
 * @version:
 */
public class Test {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        System.out.println(CollUtil.isEmpty(studentList));


    }


}
