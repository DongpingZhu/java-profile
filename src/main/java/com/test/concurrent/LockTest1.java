package com.test.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest1 {
    public static void main(String[] args){
        Lock lock = new ReentrantLock(); // 类同synchronized锁
        new Thread(()->{
            lock.lock();
            try {
                Thread.sleep(3000);
                System.out.println("aaaaa");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock(); // 需要手动释放锁
            }

        }).start();
        System.out.println("start");
    }
}
