package com.yc.algorithm.algorithm;

import java.util.Arrays;

public class MoveZeroes_283 {
    public static void main(String[] args) {
        int[] arr = {0,1,0,3,12};
        //int[] arr = {0};
        moveZeroes2(arr);
    }


    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int size = 0; //保存非0元素个数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[size++] = nums[i];
            }
        }
        if (size < nums.length) {
            for (int i = size; i < nums.length; i++) {
                nums[i] = 0;
            }
        }
        System.out.println(Arrays.toString(nums));
    }


    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     */
    public static void moveZeroes2(int[] nums) {
        int size = 0; //保存非0元素个数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != size) {
                    //交换位置
                    nums[size++] = nums[i];
                    nums[i] = 0;
                } else {
                    //如果是本身，则直接跳过
                    size ++;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
