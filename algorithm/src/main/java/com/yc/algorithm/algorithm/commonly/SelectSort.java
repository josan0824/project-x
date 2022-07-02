package com.yc.algorithm.algorithm.commonly;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSort {

    public static void main(String[] args) {

        int[] arr = {9,7,5,3,2,1,1};
        System.out.println(Arrays.toString(selectionSort(arr)));
    }

    /**
     * 选择排序
     * @param arr
     * @return
     */
    private static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //当前趟最小值为第一个
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                //交换位置
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        return arr;
    }
}
