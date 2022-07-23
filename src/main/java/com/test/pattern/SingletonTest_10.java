package com.test.pattern;

// 静态内部类形式，记住这个即可,lazy加载且线程安全
public class SingletonTest_10 {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1);
        System.out.println(instance2);

    }

}
// 静态内部类形式，记住这个即可. 外部类加载时并不需要立即加载内部类，也即不会实例化Singleton
class Singleton {
    private Singleton(){

    }
    private static class Inner{
        private static Singleton singleton = new Singleton();
    }

    public static Singleton getInstance(){
        return Inner.singleton;
    }

}
// 枚举类实现
