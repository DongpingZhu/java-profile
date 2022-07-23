package com.test.pattern.javaproxy_3;

import java.lang.reflect.Proxy;

public class Dynamic_javaProxy {
    public static void main(String[] args) {
        UserDemo1 userDemo1 = new UserDemo1();
        IUseDemo1 proxyInstance = (IUseDemo1) new ProxyFactory1(userDemo1).getProxyInstance();
//        UserDemo1 proxyInstance = (UserDemo1) new ProxyFactory1(userDemo1).getProxyInstance();
        proxyInstance.save();
        proxyInstance.func1();
        proxyInstance.func2();

    }
}
interface IUseDemo1{
    void save();
    void func1();
    void func2();
}

class UserDemo1 implements IUseDemo1 {

    @Override
    public void save() {
        System.out.println("这是待代理对象的save方法");
    }

    @Override
    public void func1() {
        System.out.println("这是待代理对象的func1方法");
    }
    @Override
    public void func2(){
        System.out.println("这是待代理对象的func2方法");
    }

}

class ProxyFactory1{
    private Object target;
    public ProxyFactory1(Object target){
        this.target = target;
    }
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy,method,args)->{
                    System.out.println("这是代理对象，开始执行--前---");
                    Object invoke = method.invoke(target, args);
                    System.out.println("这是代理对象，结束执行--后---");
                    return invoke;
                });
    }

}
