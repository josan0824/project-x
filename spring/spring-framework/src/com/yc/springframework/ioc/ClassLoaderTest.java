package com.yc.springframework.ioc;

/**
 * @author: josan_tang
 * @create_date: 2021/11/13 14:21
 * @desc:
 * @version:
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println("current loader:" + loader);
        System.out.println("parent loader:" + loader.getParent());
        System.out.println("grandparent loader:" + loader.getParent().getParent());
    }
}
