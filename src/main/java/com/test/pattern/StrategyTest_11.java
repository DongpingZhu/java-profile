package com.test.pattern;

// 策略模式，有点面向接口编程的意思
public class StrategyTest_11 {
    public static void main(String[] args) {
        Context context = new Context(new OpAdd()); // 加
        System.out.println(context.executeStrategy(10,5));
        context = new Context(new OpSubtract()); // 减
        System.out.println(context.executeStrategy(10,5));
        context = new Context(new OpMultiply()); // 乘
        System.out.println(context.executeStrategy(10,5));
    }
}

interface Strategy{
    int doWork(int a, int b);
}

class OpAdd implements Strategy{
    @Override
    public int doWork(int a, int b){
        return a+b;
    }
}

class OpSubtract implements Strategy{
    @Override
    public int doWork(int a, int b) {
        return a-b;
    }
}

class OpMultiply implements Strategy{
    @Override
    public int doWork(int a, int b) {
        return a*b;
    }
}

class Context{
    private Strategy strategy;

    public Context(Strategy strategy){ // 传入不同的策略
        this.strategy = strategy;
    }

    public int executeStrategy(int a, int b){
        return strategy.doWork(a,b);
    }
}
