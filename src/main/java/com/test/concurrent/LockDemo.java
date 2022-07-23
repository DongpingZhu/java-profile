package com.test.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);
        new Thread(productor,"生产者A").start();
        new Thread(consumer,"消费者B").start();
    }
}
class Clerk{
    private int product = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void get(){
        lock.lock();
        try{
            while(product>=1){
                System.out.println("仓库有货");
                try{
                    condition.await();// 阻塞当前线程
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+  ++product);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void sale(){
        lock.lock();
        try {
            while (product<=0){
                System.out.println("仓库缺货");
                try{
                    condition.await();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+ --product);
            condition.signalAll(); // 唤醒阻塞的线程
        }finally {
            lock.unlock();
        }
    }
}
class Productor implements Runnable{
    private Clerk clerk;
    public Productor(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run(){
        for(int i=0;i<3;i++){
            try{
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
            clerk.get();
        }
    }
}

class Consumer implements Runnable{
    private Clerk clerk;
    public Consumer(Clerk clerk){
        this.clerk = clerk;
    }
    @Override
    public void run(){
        for (int i=0;i<3;i++){
            clerk.sale();
        }
    }
}


