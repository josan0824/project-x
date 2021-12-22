package com.yc.springframework.aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib方式的拦截类
 */
public class CglibInterceptor implements MethodInterceptor {

    /**
     * 获取代理对象
     * @param targetClass
     * @return
     */
    public Object getProxy(Class targetClass) {
        Enhancer enhancer = new Enhancer();
        //设置回调对象
        enhancer.setCallback(this);
        //设置父类为被代理的对象
        enhancer.setSuperclass(targetClass);
        //创建子类实例（代理对象）
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //System.out.println("cglib动态代理 调用业务方法之前");
        //通过代理类调用父类中的方法
        Object reVal = methodProxy.invokeSuper(obj, args);
        //System.out.println("cglib动态代理 调用业务方法之后");
        return reVal;
    }
}
