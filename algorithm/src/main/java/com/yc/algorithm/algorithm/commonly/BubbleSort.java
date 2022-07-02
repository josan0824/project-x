package com.yc.algorithm.algorithm.commonly;

import java.util.Arrays;

/**
 * 冒泡排序
 * 冒泡排序只会操作相邻的两个数据。每次冒泡操作都会对相邻的两个元素进行比较，
 * 看是否满足大小关系要求。如果不满足就让它俩互换。
 * 一次冒泡会让至少一个元素移动到它应该在的位置，重复 n 次，就完成了 n 个数据的排序工作。
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {4,5,6,3, 2, 1};
        System.out.println(Arrays.toString(bubbleSort(arr)));
    }

    /**
     * 冒泡排序
     * @param arr 待排序的数组
     * @return
     */
    private static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i ++) {
            //标记是否有交换
            boolean flag = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    //交换位置
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                //没有交换则证明已经有序，则不需要继续排序
                break;
            }
        }
        return arr;
    }
}
