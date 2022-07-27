package com.test.concurrent.sync;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier可以循环执行
 */
public class CyclicBarrier2 {
    public static void main(String[] args) {
        int num = 17;
        // 另一种构造方法
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> System.out.println("--------5人凑够-----------"));
        for (int i = 0; i < num; i++) {
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+"已到达，等待其他人");
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "人员-"+i).start();

        }

    }
}
