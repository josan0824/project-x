package com.yc.algorithm.algorithm.commonly;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] nums = new int[]{11, 8,3,9,1,2,5};
        sortArray(nums);
        System.out.println(Arrays.toString(nums));
    }


    private static void sortArray(int[] nums) {
        mergeSortPart(nums, 0, nums.length - 1);
    }

    /**
     * 针对数组排序
     * @param nums 排序数组
     * @param left 左边位置
     * @param right 右边位置
     */
    private static void mergeSortPart(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (right - left) / 2 + left;
        //保证左边有序
        mergeSortPart(nums,left, mid);
        //保证右边有序
        mergeSortPart(nums, mid + 1, right);
        //两个有序数组合并
        merge(nums, left, mid, right);
    }

    /**
     * 合并两个有序数组
     * @param nums1
     * @param left
     */
    private static void merge(int[] nums1, int left, int mid, int right) {
        //创建一个临时数组
        int[] temp = new int[right - left + 1];
        int size = 0;
        //n从左边数组位置开始计数，m从右边数组位置开始计数
        int n = left, m = mid + 1;
        //如果n <= mid && m <= right,即遍历两个数组
        //如果那边的数组更小，则把它的数据加入到临时数组中,且对应计数表示n/m后移，且临时数组的元素个数也自增
        //这里需要注意的是，当一个数组元素遍历完后，另外一个数组可能存在没有遍历完的情况，需要后续处理加入到临时数组
        while (n <= mid && m <= right) {
            if (nums1[n] <= nums1[m]) {
                temp[size++] = nums1[n];
                n++;
            } else {
                temp[size++] = nums1[m];
                m++;
            }
        }

        if (n <= mid) {
            //表示前一个数组还有元素没有参与排序，则放入数组末尾
            for (int i = n; i <= mid; i++) {
                temp[size++] = nums1[i];
            }
        }

        if (m <= right) {
            //表示后一个数组还有元素没有参与排序，则放入数组末尾
            for (int i = m; i <= right; i++) {
                temp[size++] = nums1[i];
            }
        }

        //把临时数组的数据赋值到原始数组中的位置
        for (int i = 0; i < temp.length; i++) {
            nums1[left + i] = temp[i];
        }
    }

}
