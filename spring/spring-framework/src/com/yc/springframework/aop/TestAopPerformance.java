package com.yc.springframework.aop;

import java.lang.reflect.Proxy;

public class TestAopPerformance {
    public static void main(String[] args) {
        //测试创建性能
        //testCreate(10000);
       testCall(10000);
    }

    /**
     * 测试调用耗时情况
     */
    public static void testCall(int times) {
        //先进行创建
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Animal animal = new AnimalProxy(dog2);
        Animal jdkTarget = new Dog();
        JDKProxyHandler jdkProxyHandler = new JDKProxyHandler(jdkTarget);
        Animal jdkAnimal = (Animal) Proxy.newProxyInstance(jdkTarget.getClass().getClassLoader(), jdkTarget.getClass().getInterfaces(), jdkProxyHandler);
        CglibInterceptor cglibInterceptor = new CglibInterceptor();
        Cat cat = (Cat) cglibInterceptor.getProxy(Cat.class);
        //测试调用时间
        //1.不使用代理
        long begin1 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            dog1.call();
        }
        long end1 = System.currentTimeMillis();
        System.out.println("不使用代理调用耗时：" + (end1 - begin1)  + "ms");

        //2.静态代理
        long begin2 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            animal.call();
        }
        long end2 = System.currentTimeMillis();
        System.out.println("静态代理调用耗时：" + (end2 - begin2)  + "ms");

        //3.jdk动态代理
        long begin3 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            jdkAnimal.call();
        }
        long end3 = System.currentTimeMillis();
        System.out.println("jdk动态代理调用耗时：" + (end3 - begin3) + "ms");

        //4.cglib动态代理
        long begin4 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            cat.call();
        }
        long end4 = System.currentTimeMillis();
        System.out.println("cglib动态代理调用耗时：" + (end4 - begin4) + "ms");
    }

    /**
     * 测试创建耗时情况
     */
    public static void testCreate(int times) {
        //创建对象个数
        //1.不使用代理
        long begin1 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            Dog dog = new Dog();
        }
        long end1 = System.currentTimeMillis();
        System.out.println("不使用代理创建耗时：" + (end1 - begin1)  + "ms");

        //2.静态代理
        long begin2 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            Dog dog = new Dog();
            Animal animal = new AnimalProxy(dog);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("静态代理创建耗时：" + (end2 - begin2)  + "ms");

        //3.jdk动态代理
        long begin3 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            Animal jdkTarget = new Dog();
            JDKProxyHandler jdkProxyHandler = new JDKProxyHandler(jdkTarget);
            Animal jdkAnimal = (Animal) Proxy.newProxyInstance(jdkTarget.getClass().getClassLoader(), jdkTarget.getClass().getInterfaces(), jdkProxyHandler);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("jdk动态代理创建耗时：" + (end3 - begin3) + "ms");

        //4.cglib动态代理
        long begin4 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            CglibInterceptor cglibInterceptor = new CglibInterceptor();
            Cat cat = (Cat) cglibInterceptor.getProxy(Cat.class);
        }
        long end4 = System.currentTimeMillis();
        System.out.println("cglib动态代理创建耗时：" + (end4 - begin4) + "ms");
    }
}
