package com.yc.core.service.impl;

import cn.hutool.core.bean.BeanUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: josan_tang
 * @create_date: 2022/5/19 9:59
 * @desc:
 * @version:
 */
public class HuToolServiceImpl {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("gaga");
        s1.setAge(1);
        Student s2 = new Student();

        BeanUtil.copyProperties(s1, s2);
    }


    static class Student {
        @Setter
        @Getter
        String name;

        @Setter
        int age;
    }
}
