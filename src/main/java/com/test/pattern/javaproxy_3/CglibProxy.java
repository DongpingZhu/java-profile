package com.test.pattern.javaproxy_3;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy {
    public static void main(String[] args) {
        TargetDemo target = new TargetDemo();
        TargetDemo proxyInstance = (TargetDemo) new CglibProxyFactory(target).getProxyInstance();

        proxyInstance.save();
        proxyInstance.func1();


    }
}
// cglib库动态代理：目标对象不用实现接口

class TargetDemo{
    public void save(){
        System.out.println("target对象的save()");
    }
    public void func1(){
        System.out.println("target的func1方法执行了");
    }
}

class CglibProxyFactory implements MethodInterceptor {
    private Object target;
    public CglibProxyFactory(Object target){
        this.target = target;
    }
    public Object getProxyInstance(){
        Enhancer en = new Enhancer();
        en.setSuperclass(target.getClass());
        en.setCallback(this);
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        Object ret = method.invoke(target, objects);
        System.out.println("cglib proxy 任务开始了");
        return ret;
    }
}
