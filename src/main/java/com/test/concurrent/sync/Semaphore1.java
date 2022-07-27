package com.test.concurrent.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 用来控制线程的并发数，可以协调各个线程，以达到合理的使用公共资源。
 * 如果我们允许的的许可证数量为1 ，那么它的效果与锁相似。
 */
public class Semaphore1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        Semaphore semaphore = new Semaphore(1);// 也就是允许1个线程并发,效果等同锁
        for (int i = 0; i < 20; i++) {
            executorService.submit(()->{
                try {
                    semaphore.acquire(); // 获取许可
                    System.out.println("耗时操作"+Thread.currentThread().getName());
                    System.out.println(Thread.currentThread().getName()+"正在执行");
                    TimeUnit.SECONDS.sleep(1);
                    semaphore.release(); //释放许可
                }catch (Exception e){
                    e.printStackTrace();
                }

            });

        }
        executorService.shutdown();

    }
}
