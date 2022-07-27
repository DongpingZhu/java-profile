package com.test.concurrent.sync;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier与CountDownLatch类似，当等待到一定数量的线程后开始执行某个任务。
 * 区别：countDownLatch是await等待其他线程，通过countDown达到一定数才执行。
 *       而 CyclicBarrier则是直接看await等待的数量，达到一定数量直接全部执行
 */

public class CyclicBarrier1 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("男朋友已到达，正在等女朋友");
                cyclicBarrier.await();// 开始等待后，计数自动减1
                System.out.println("女朋友已到达，开始约会");

            }catch (Exception e){
                e.printStackTrace();
            }
        },"男朋友").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("女朋友已到达，正在等男朋友");
                cyclicBarrier.await();
                System.out.println("男朋友已到达，开始约会");

            }catch (Exception e){
                e.printStackTrace();
            }
        },"女朋友").start();

    }
}
