package com.test.concurrent;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class CallableTest1 {
    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> new Random().nextInt(100));


        new Thread(futureTask).start();
        Thread.sleep(5000);
//        System.out.println(futureTask.get());

        ExecutorService executorService = Executors.newWorkStealingPool();
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        Supplier<Integer> supplier = ()-> new Random().nextInt();

        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(supplier);


    }
}
