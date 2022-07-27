package com.test.concurrent.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 一个线程等待多个线程（可理解为最后的时刻还是多个线程等待一个线程，等同于第一个样例）
 */
public class CountDownLatch2 {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for(int i=0;i<5;i++){
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+"到达大巴车");
                    countDownLatch.countDown();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }, "人员-"+i).start();
        }
        System.out.println("大巴正在等待中...");
        countDownLatch.await();
        System.out.println("所有人到齐，出发");
    }
}
