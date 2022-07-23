package com.test.algorithm;


import java.util.Arrays;

public class SortDemo {
    public static void main(String[] args) throws Exception{

        int[] array1 = new int[]{100,20,19,2,3,3,1,8,0,4,7,6,5,19};
        int[] array = new int[]{8,4,5,7,1,3,6,2};
        System.out.println("排序前：" + Arrays.toString(array1));
//        bubble(array);
//        insert(array);
//        select(array);
//        quick(array,0,array.length-1);
//        mergeSort(array, 0, array.length-1);
        heapSort(array1);
        System.out.println("排序后：" + Arrays.toString(array1));
    }


    public static void quick(int[] arr, int left, int right){
        if(left>right) return; // 递归终止条件
        int i = left;
        int j = right;
        int pivot = arr[left];
        while (i<j){
            while (arr[j]>=pivot && i<j) j--;//右指针碰到小于pivot为止
            while (arr[i]<=pivot && i<j) i++;//左指针碰到大于pivot为止
            if(i<j){ // 此时交换左右指针对应的值
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        // 左右指针走一遍，交换pivot值，
        arr[left] = arr[i];
        arr[i] = pivot;
        // 对左右两部分继续上面的操作
        quick(arr,left,j-1);
        quick(arr,j+1,right);
    }

    public static void mergeSort(int[] arr, int left, int right){
        if(left < right){ // 递归终止条件
            int mid = (left+right) / 2; // 中部位置下标
            // 分解
            mergeSort(arr, left, mid); // 左半
            mergeSort(arr, mid+1, right); //右半
            // 合并
            merge(arr, left, right, mid);
        }

    }
    private static void merge(int[] arr, int left, int right, int mid){
        int[] temp = new int[right-left+1];
        int i = left; // 左半部分初始下标
        int j = mid + 1; // 右半部分初始下标
        int t = 0; // temp数组初始下标
        while (i<=mid && j<=right){
            if(arr[i]<arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while (i<=mid){
            temp[t++] = arr[i++];
        }
        while (j<=right){
            temp[t++] = arr[j++];
        }
        //调整数组顺序
        for (int k = 0; k < temp.length; k++) {
            arr[k+left] = temp[k];
        }
//        System.out.println("合并temp中:"+ Arrays.toString(temp));
        System.out.println("合并array中:"+ Arrays.toString(arr));

    }

    public static void heapSort(int[] arr){
        // 1. 基于给定的数组构建一个大顶堆；
        int length = arr.length;
        for(int i=(length-2)/2;i>=0;i--){ //i表示数组索引，最大为2i+2 < length
            sink(arr,i,length);
        }
        // 2. 从大顶堆中取出当前最大值，再将剩余部分下沉使之再次成为大顶堆，循环直至没有数据
        for(int i=length-1;i>0;i--){
            int temp = arr[0]; // 堆顶的最大值
            arr[0] = arr[i]; // 最后一个移到顶部，再下沉使其堆化
            arr[i] = temp; // arr[0]为堆顶元素，即最大值，将其放在当前数组最后面
            length--;
            sink(arr,0, length); // 每次下沉都是排除数组最后面那部分升序的序列:length--
        }
    }
    private static void sink(int[] arr,int index, int length){
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
            sink(arr,curr,length); // 基于此节点继续下沉
        }
    }

    public static void select(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length ; j++) {
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            int minvalue = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = minvalue;
        }

    }

    public static void insert(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int tempIndex = i-1;
            while (tempIndex>=0 && temp<arr[tempIndex]){
                arr[tempIndex+1] = arr[tempIndex];//后移
                tempIndex--;
            }
            arr[tempIndex+1] = temp;
        }
    }

    public static void bubble(int[] arr) throws Exception{
        if(arr == null){
            throw new Exception("array is null!");
        }
        if(arr.length<2){
            return;
        }
        int tmp;
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    tmp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }



}
