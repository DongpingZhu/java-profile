package com.test.fp;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class demo1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("this", "is", "a", "list", "of", "string");
        List<String> collect = list.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
//        collect.stream().forEach(System.out::println);
//        generate();
//        iterate();
        createStream();
        Objects.requireNonNull(collect);
        int a = 0;
        Integer b = 0;

    }
    // 无限流1：
    private static void generate(){
//        Stream.generate(Math::random).limit(10).forEach(System.out::println);
        Stream.generate(UUID::randomUUID).limit(10).forEach(System.out::println);
    }
    // 无限流2：
    private static void iterate(){
        Stream.iterate(0,x->x+2).limit(10).forEach(System.out::println);
    }
    // 创建流
    private static void createStream(){
        IntStream.range(0,10).forEach(System.out::println);
        IntStream.rangeClosed(0,10).forEach(System.out::println);
    }

}
