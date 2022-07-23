package com.test.concurrent;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo1 {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 线程不安全
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 线程安全

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ThreadPoolTest threadPoolTest = new ThreadPoolTest();
        for (int i = 0; i < 10; i++) {
            executorService.submit(threadPoolTest);
        }
        executorService.shutdown();

    }
    static class ThreadPoolTest implements Runnable{
        @Override
        public void run() {

            String format = simpleDateFormat.format(new Date());
            try {
                Date parse = simpleDateFormat.parse(format);
                String format1 = simpleDateFormat.format(parse);
                System.out.println(Thread.currentThread().getName()+"线程是否安全：" + format.equals(format1));
            }catch (Exception e){
                System.out.println(Thread.currentThread().getName()+"格式化失败");
            }
        }
    }
}
