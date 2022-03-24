package com.yc.algorithm.algorithm.leetcode;

import java.util.Arrays;

public class RemoveElement_27 {
    public static void main(String[] args) {
        int[] arr = {3,2,2,3};
        System.out.println(removeElement(arr, 3));
    }

    public static int removeElement(int[] nums, int val) {
        int k = 0; //k表示非key个数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return k ;
    }

}