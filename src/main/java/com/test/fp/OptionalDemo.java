package com.test.fp;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalDemo {
    public static void main(String[] args) {
        String str = null;
        Optional<String> str1 =  Optional.ofNullable(str);
        System.out.println(str1.orElse("null"));
        List<Integer> collect = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)).flatMap(item -> item.stream()).collect(Collectors.toList());
        System.out.println(collect.toString());


    }
}
