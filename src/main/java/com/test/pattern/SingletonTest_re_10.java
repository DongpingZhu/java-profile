package com.test.pattern;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonTest_re_10 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> System.out.println(Boss.getInstance()));
        }
        Boss.foo();
        Boss1.foo();



    }
}
// 这种是lazy加载
class Boss{
    private Boss(){
        System.out.println("Boss 构造");
    }
    private static class LazyLoader { // 静态内部类或非静态内部类都不会因为外部类的加载而加载，延后加载了。

        private static final Boss inst = new Boss();

    }
    public static Boss getInstance(){
        System.out.println("getInstance");
        return LazyLoader.inst;

    }
    public static void foo(){
        System.out.println("foo>>>");
    }
}
// 这种是非lazy加载
class Boss1{
    private Boss1(){
        System.out.println("boss1 构造");
    }
    private static Boss1 instance = new Boss1();  // 类加载时，这个static变量会初始化，即会立即实例化

    public static Boss1 getInstance(){
        System.out.println("boss1---getInstance");
        return instance;
    }
    public static void foo(){
        System.out.println("foo1>>>>>>");
    }

}
