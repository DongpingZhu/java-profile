package com.test.cvat;

import java.lang.reflect.Array;
import java.util.*;

public class Demo {
    public static void main(String[] args) {

        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(3);
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(99);
        hashSet.add(-2);
        for (Integer integer : hashSet) {
            System.out.println(integer);
        }


    }
}
