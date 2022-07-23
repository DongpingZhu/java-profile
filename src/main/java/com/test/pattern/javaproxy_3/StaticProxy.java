package com.test.pattern.javaproxy_3;

public class StaticProxy {
    public static void main(String[] args) {
        User user = new User();
        UserProxy userProxy = new UserProxy(user);
        userProxy.save();
    }
}

interface IUser{
    void save();
}

class User implements IUser{
    @Override
    public void save() {
        System.out.println("target对象");
    }
}
class UserProxy implements IUser{
    private IUser target;

    public UserProxy(IUser target){
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("proxy对象执行额外功能");
        target.save();
    }
}
