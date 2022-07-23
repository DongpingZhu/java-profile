package com.test.concurrent;

public class ThreadLifecyle {
    public static void main(String[] args) {
        Object lock = new Object(); // 定义一个对象锁
        new WaitThread(lock).start(); // 两个线程使用同一个锁，因此可以sync
        new NotifyThread(lock).start();

    }
}

class NotifyThread extends Thread{
    private final Object lock;
    public NotifyThread(Object lock){
        super();
        this.lock = lock;
    }
    @Override
    public void run(){
        synchronized (lock){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("开始notify, time="+System.currentTimeMillis());
            lock.notify();
            System.out.println("结束notify, time="+System.currentTimeMillis());
        }
    }
}
class WaitThread extends Thread{
    private final Object lock;
    public WaitThread(Object lock){
        super();
        this.lock = lock;
    }
    @Override
    public void run(){
        synchronized (lock){
            try {
                long start = System.currentTimeMillis();
                System.out.println("开始wait time="+ start);
                lock.wait();
                long end = System.currentTimeMillis();
                System.out.println("结束wait time="+ end);
                System.out.println("wait time = "+(end-start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
