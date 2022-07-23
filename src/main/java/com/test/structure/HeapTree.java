package com.test.structure;
// 二叉堆的使用场景：1. 堆排序；2. PriorityQueue优先队列
// 二叉堆核心两堆化操作：shiftUp, shiftDown. 在构建，添加，删除操作种都需要进行退堆化
// 增加：放在数组最后面，然后shiftUp操作
// 删除：删除堆顶，然后shiftDown操作

import java.lang.reflect.Array;
import java.util.Arrays;

public class HeapTree {

    public static int[] buildMaxHeap(int[] arr){ // 构建大顶堆
        int length = arr.length;
        for(int i=(length-2)/2;i>=0;i--){ //i表示数组索引，最大为2i+2 < length
            shiftDown(arr,i);
        }
        return arr;
    }
    public static int[] buildMinHeap(int[] arr){
        int length = arr.length;
        for(int i=(length-2)/2;i>=0;i--){
            shiftUp(arr,i);
        }
        return arr;
    }
    // 添加：放在最后面，再进行上浮
    public static int[] add(int[] arr, int val){
        int[] newArr = new int[arr.length+1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        newArr[newArr.length-1]=val;
        return buildMinHeap(newArr);
    }

    // 删除：一般是删除堆顶元素，删完后进行下沉
    public static int[] delete(int[] arr){
        int[] minHeap = buildMinHeap(arr);
        int[] newArr = new int[arr.length-1];
        System.arraycopy(minHeap,1,newArr,0,arr.length-1);
        return buildMinHeap(newArr);
    }



    // 核心的两方法，再增删后进行自我调正已满足堆定义
    public static void shiftUp(int[] arr,int index){
        int length = arr.length;
        int left = 2*index + 1; // 左子节点下标
        int right = 2*index + 2; // 右子节点下标
        int curr = index; // 当前元素下标
        // 左边下沉
        if (left < length && arr[curr] > arr[left]) {
            curr = left;
        }
        // 右边下沉
        if (right < length && arr[curr] > arr[right]) {
            curr = right;
        }
        // 下标不相等，说明调换过了,那么进行替换
        if(curr != index){
            int temp = arr[index];
            arr[index] = arr[curr];
            arr[curr] = temp; // 交换下沉起始节点的值
            shiftUp(arr,curr); // 基于此节点继续下沉
        }
    }
    public static void shiftDown(int[] arr,int index){
        int length = arr.length;
        int left = 2*index + 1; // 左子节点下标
        int right = 2*index + 2; // 右子节点下标
        int curr = index; // 当前元素下标
        // 左边下沉
        if (left < length && arr[curr] <arr[left]) {
            curr = left;
        }
        // 右边下沉
        if (right < length && arr[curr] < arr[right]) {
            curr = right;
        }
        // 下标不相等，说明调换过了,那么进行替换
        if(curr != index){
            int temp = arr[index];
            arr[index] = arr[curr];
            arr[curr] = temp; // 交换下沉起始节点的值
            shiftDown(arr,curr); // 基于此节点继续下沉
        }
    }

}
