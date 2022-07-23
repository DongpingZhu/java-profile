package com.test.structure;

import java.util.Arrays;

public class TestHeapTree {
    public static void main(String[] args) {
        int[] array = new int[]{8,4,5,7,1,3,6,2};
//        int[] heapArr = HeapTree.buildMinHeap(array);// 构建小顶堆
        int[] heapArr = HeapTree.delete(array);

        System.out.println(Arrays.toString(heapArr));

    }
}
