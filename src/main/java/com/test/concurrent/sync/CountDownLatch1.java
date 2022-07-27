package com.test.concurrent.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 多个线程等待一个线程
 *  总结：只有当countDownLatch中的值变为0,其他被await阻塞的线程才会执行
 */
public class CountDownLatch1 {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + "已准备好");
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName()+ "开始跑 ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"选手1").start();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + "已准备好");
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName()+ "开始跑 ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"选手2").start();

        System.out.println("裁判：2s预备---");
        TimeUnit.SECONDS.sleep(2);
        countDownLatch.countDown(); // 只有这个线程执行countDown()后，上面1变为0,其他所有阻塞的线程才会执行
        System.out.println("裁判：跑---");

    }
}
