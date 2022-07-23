package com.test.pattern.javaproxy_3;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Dynamic_cglibProxy {
    public static void main(String[] args) {
        TargetDemo1 targetDemo1 = new TargetDemo1();
        TargetDemo1 proxyInstance = (TargetDemo1) new CglibProxyFactory1(targetDemo1).getProxyInstance();
        proxyInstance.save();
        proxyInstance.func1();
        proxyInstance.func2();
    }
}

class TargetDemo1{
    public void save(){
        System.out.println("这是待代理对象的save方法");
    }
    public void func1(){
        System.out.println("这是待代理对的func1方法");
    }
    public void func2(){
        System.out.println("这是待代理对的func2方法");
    }
}
class CglibProxyFactory1 implements MethodInterceptor{
    private Object target;
    public CglibProxyFactory1(Object target){
        this.target = target;
    }
    public Object getProxyInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("--------前-------------------");
        Object invoke = method.invoke(target, objects);
        System.out.println("------后------，可以添加其他额外的功能！");

        return invoke;
    }
}
