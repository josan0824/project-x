package com.yc.springboot;

import com.yc.springboot.service.MyService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author: josan_tang
 * @create_date: 2022/4/19 13:38
 * @desc: 测试类
 * @version:
 */
public class MyControllerTest extends BaseApplicationTests{

    @Resource
    private MyService myService;

    @Test
    public void testMyService() {
        //System.out.println(myService.testJunit());
        Assert.assertSame("数量不对", 6, myService.testJunit());
    }
}
