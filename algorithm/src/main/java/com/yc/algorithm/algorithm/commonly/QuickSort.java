package com.yc.algorithm.algorithm.commonly;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: josan_tang
 * @create_date: 2022/6/29 12:29
 * @desc:
 * @version:
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {6,11, 3,9,8};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr){
        quickSort2(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right){
        if (left >= right) return; //左数不小于右数，直接返回
        int pivot = arr[left]; //永远以最左边的数为基准
        int i = left, j = right;
        while (i < j){
            while (arr[j] >= pivot && i < j) {
                --j; //从右向左遇到小于基准的数就停止
            }
            while (arr[i] <= pivot && i < j) {
                ++i; //从左向右遇到大于基准的数就停止
            }
            if (i < j){ //交换
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        //基准数归位（位置已确定）
        arr[left] = arr[i];
        arr[i] = pivot;
        //分别对基准数两端的子数组进行快排
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }


    /**
     * 快速排序
     * @param arr
     * @param left
     * @param right
     */
    private static void quickSort2(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        //得到分区的位置
        int i = partition(arr, left, right);
        //排序左边的分区
        quickSort2(arr, left, i- 1);
        //排序右边的分区
        quickSort2(arr, i + 1, right);
    }

    /**
     * 分区函数
     * @param arr
     * @param left
     * @param right
     * @return 分区后的位置
     */
    private static int partition(int[] arr, int left, int right) {
        //随机得到主元
        int pivot = getPivot(arr, left, right);
        //i表示分区点的位置，则[left, i - 1]表示小于pivot的区间，遍历数组时，发现有小于pivot的值，则跟i交换，并让i++，加入到该区间
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                //交换i和j的位置
                swap(arr, i , j);
                i++;
            }
        }
        //最后交换
        swap(arr, i , right);
        return i;
    }

    /**
     * 获取主元
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int getPivot(int[] arr, int left, int right) {
        int pivot = new Random().nextInt(right - left + 1) + left;
        swap(arr, pivot, right);
        return arr[right];
    }

    /**
     * 交换
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

}
