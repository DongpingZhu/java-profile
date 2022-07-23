package com.test.concurrent;


public class SyncClass {
    public static void main(String[] args) {
        SyncThread syncThread1 = new SyncThread();
        SyncThread syncThread2 = new SyncThread();
        new Thread(syncThread1,"syncThread1").start();
        new Thread(syncThread2,"syncThread2").start();

    }
}

class SyncThread implements Runnable{
    private static int count;
    public SyncThread(){
        count = 0;
    }
    public synchronized static void method(){
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + count++);
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void run(){
//        method();
        synchronized (SyncThread.class){
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + count++);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
