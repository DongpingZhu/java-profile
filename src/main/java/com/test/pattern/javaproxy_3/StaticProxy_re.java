package com.test.pattern.javaproxy_3;

public class StaticProxy_re {
    public static void main(String[] args) {
        User1 user1 = new User1();
        User1Proxy user1Proxy = new User1Proxy(user1);
        user1Proxy.save();
    }


}

interface IUser1{
    void save();
}
class User1 implements IUser1{

    @Override
    public void save() {
        System.out.println("target对象");
    }
}
class User1Proxy implements IUser1{
    private IUser1 target;

    public User1Proxy(IUser1 user1){
        this.target = user1;
    }
    @Override
    public void save() {
        System.out.println("代理对象，除了代理原本的功能，还可以在此处执行额外的功能！");
        target.save();

    }
}
