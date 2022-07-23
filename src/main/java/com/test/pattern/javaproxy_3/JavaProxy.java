package com.test.pattern.javaproxy_3;


import java.lang.reflect.Proxy;

public class JavaProxy {

    public static void main(String[] args) {
        IUserDemo target = new UserDemo();
        System.out.println("target对象："+target.getClass());
        IUserDemo proxyInstance = (IUserDemo)new ProxyFactory(target).getProxyInstance();
        System.out.println("proxy对象："+proxyInstance.getClass());
        proxyInstance.save();
        proxyInstance.func1();
    }
}

interface IUserDemo{
    void save();
    void func1();
}

class UserDemo implements IUserDemo{
    @Override
    public void save() {
        System.out.println("target对象");
    }

    @Override
    public void func1() {
        System.out.println("target对象的func1算法");
    }
}

// java原生提供的代理模式：代理对象无需实现接口。但目标对象必须实现接口
class ProxyFactory{
    private Object target;
    public ProxyFactory(Object target){
        this.target = target;
    }
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("proxy开始额外功能...");
                    Object ret = method.invoke(target, args);
                    System.out.println("proxy结束额外功能...");
                    return ret;
                }
        );
    }
}
