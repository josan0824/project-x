package com.yc.springframework.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK动态代理
 */
//实现InvocationHandler接口
public class JDKProxyHandler implements InvocationHandler {

    //目标业务类
    private Object target;

    //传入目标业务类
    public JDKProxyHandler(Object target) {
        this.target = target;
    }

    /**
     *
     * @param proxy 代理对象
     * @param method  被代理目标实例的某个方法
     * @param args 被代理实例的某个方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk动态代理 调用业务方法之前");
        //利用反射调用业务类的目标方法
        Object reVal = method.invoke(target, args);
        System.out.println("jdk动态代理 调用业务方法之后");
        return reVal;
    }
}
