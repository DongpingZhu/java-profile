package com.test.concurrent.sync;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用于进行线程间数据的交换，它有一个同步点，当两个线程到达同步点时可以将各自的数据传给对方，
 * 如果一个线程先到达同步点则会等待另一个到达同步点，到达同步点后调用 exchange方法可以传递自己的数据并且获得对方的数据。
 * 仅可用作两个线程之间的信息交换
 */
public class Exchanger1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Exchanger<String> exchanger = new Exchanger<>();
        executorService.submit(()->{
            try {
                String s = exchanger.exchange("线程1的数据");
                System.out.println("线程1获取的交换数据为："+ s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        executorService.submit(()->{
            try {
                String s = exchanger.exchange("线程2的数据");
                System.out.println("线程2获取的交换数据为："+ s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();

    }

}
