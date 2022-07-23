package com.test.concurrent;

import java.util.concurrent.*;

// JUC提供的同步器，用于并发编程中保证线程安全
public class SyncTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newWorkStealingPool();

    }

}
