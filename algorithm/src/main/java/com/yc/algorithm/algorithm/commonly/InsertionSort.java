package com.yc.algorithm.algorithm.commonly;

import java.util.Arrays;

/**
 * @author: josan_tang
 * @create_date: 2022/6/29 11:22
 * @desc: 插入排序
 * @version:
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[]  arr = {4,5,6,1,3,2};
        System.out.println(Arrays.toString(insertSort(arr)));
    }

    /**
     * 插入排序
     * @param arr
     * @return
     */
    private static int[] insertSort(int[] arr) {
        for (int i = 1; i < arr.length; ++i ) {
            int value = arr[i];
            int j = i - 1;
            for (; j >= 0; --j) {
                if (arr[j] > value) {
                    //位置向后移动
                    arr[j+1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j+1] = value;
        }
        return arr;
    }
}
