package com.test.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo2 {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("main1111-----------");

        final ReentrantLock mLock = new ReentrantLock();

        new Thread(() -> {
            for(int i = 0; i < 3; i++){
                try{
                    mLock.lock();
                    System.out.println("thread 2 running i = " + i + " start");
                    Thread.sleep(1000);
                    System.out.println("thread 2 running i = " + i + " end");
                }catch(Exception e){

                }finally{
                    mLock.unlock();
                }
            }

        }, "AAA").start();

        Thread.sleep(100); // 暂停主线程

        for(int i = 0; i < 3; i++){
            try{
                mLock.lock();
                System.out.println("thread main running i = " + i + " start");

                Thread.sleep(3000);
                System.out.println("thread main running i = " + i + " end");
            }catch(Exception e){

            }finally{
                mLock.unlock();
            }
        }
        System.out.println("main2222-----------");
    }

}
