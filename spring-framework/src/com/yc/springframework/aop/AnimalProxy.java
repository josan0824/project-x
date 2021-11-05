package com.yc.springframework.aop;

/**
 * 动物的代理类
 */
public class AnimalProxy implements Animal{
    private Animal animal;

    //传入被代理的对象
    public AnimalProxy(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void call() {
        System.out.println("调用业务方法之前执行");
        //调用被代理对象的业务方法
        animal.call();
        System.out.println("调用业务方法之后执行");
    }
}
