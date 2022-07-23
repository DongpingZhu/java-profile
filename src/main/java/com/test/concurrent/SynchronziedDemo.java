package com.test.concurrent;

import java.util.concurrent.TimeUnit;

public class SynchronziedDemo{
    public static final Object lock = new Object();

    public static void main(String[] args) {

        Account zzddpp = new Account("zzddpp", 10000.0f);
        AccountOp accountOp = new AccountOp(zzddpp);
        final int THREAD_NUM = 10;
        Thread[] threads = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new Thread(accountOp, "执行到Thread: " + i);
            threads[i].start();
        }
    }

}


class AccountOp implements Runnable{
    private Account account;
    public AccountOp(Account account){
        this.account = account;
    }
    public void run(){
        synchronized (SynchronziedDemo.lock){ // 一个Object对象就行,account对象也行
            account.deposit(500);
            account.withdraw(500);
            System.out.println(Thread.currentThread().getName()+":"+account.getBalance());
        }
    }
}

class Account{
    String name;
    float amount;
    public Account(String name, float amount) {
        this.name = name;
        this.amount = amount;
    }
    public void deposit(float amt) { // 存钱
        amount += amt;
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void withdraw(float amt){ // 取钱
        amount -= amt;
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public float getBalance(){
        return amount;
    }
}
