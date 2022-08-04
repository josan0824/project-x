package com.yc.algorithm.algorithm.leetcode;

public class SortColors_75 {

    //[2,0,2,1,1,0]
    public void sortColors(int[] nums) {
        //思路分析
        //使用三路快排
        //用left表示0的结束；right表示2的开始

        int left = -1, right = nums.length;
        //n = 6
        for (int i = 0; i < right; ) {
            if (nums[i] == 2) {
                //把2放到最后
                right --;
                swap(nums, i, right);
                //right = 4;
                //[0,0,1,1,2,2]
            } else if (nums[i] == 0) {
                left ++;
                //left = 1
                swap(nums, i, left);
                //[0,0,2,1,1,2]
                i++;
                //i  = 2
            } else {
                i++;
            }
        }

    }

    /**
     * 交换数据
     * @param nums
     * @param i
     * @param j
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
