package com.test.fp.stream;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

public class Demo {

    public static void main(String[] args) {
        testConsumer();
    }

    public static void testConsumer(){
        // Consumer<T>: accept(T t)
        Consumer<String> consumer = System.out::println;
        consumer.accept("hello");
        StringBuilder sb = new StringBuilder("Hello ");
        Consumer<StringBuilder> consumer_accept = (str) -> str.append("Jack! ");
        Consumer<StringBuilder> consumer_andThen = (str) -> str.append("Bob!");
        consumer_accept.andThen(consumer_andThen).accept(sb);
        System.out.println(sb.toString());  // Hello Jack! Bob!
    }
    public static void testFunction(){
        Function function = null;
        BinaryOperator binaryOperator = null;
    }



}
