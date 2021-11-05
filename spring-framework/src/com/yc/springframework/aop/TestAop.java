package com.yc.springframework.aop;

import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class TestAop {
    public static void main(String[] args) {
/*        //1.静态代理
        //创建被代理的对象
        Dog dog = new Dog();
        //创建代理对象，并把被代理的对象通过构造方法传入
        Animal animal = new AnimalProxy(dog);
        //调用代理类的方法
        animal.call();*/

        //2.jdk动态代理
        //被代理的目标业务类
/*        Object jdkTarget = new Dog();
        //将目标业务类和横切代码编织到一起
        JDKProxyHandler jdkProxyHandler = new JDKProxyHandler(jdkTarget);
        //创建代理对象
        Animal jdkAnimal = (Animal) Proxy.newProxyInstance(TestAop.class.getClassLoader(), jdkTarget.getClass().getInterfaces(), jdkProxyHandler);
        //调用代理类的方法
        jdkAnimal.call();*/

        //3.cglib动态代理
        //方法的拦截对象
        CglibInterceptor cglibInterceptor = new CglibInterceptor();
        Cat cat = (Cat) cglibInterceptor.getProxy(Cat.class);
        //调用代理类的方法
        cat.call();
    }
}
