package com.yc.springboot;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author: josan_tang
 * @create_date: 2022/4/19 13:34
 * @desc:
 * @version:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BaseApplicationTests {

    @Before
    public void init() {
        System.out.println("开始测试-----");
    }

    @After
    public void after() {
        System.out.println("测试结束-----");
    }
}
