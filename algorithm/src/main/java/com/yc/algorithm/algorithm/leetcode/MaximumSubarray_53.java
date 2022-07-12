package com.yc.algorithm.algorithm.leetcode;

/**
 * @author: josan_tang
 * @create_date: 2022/7/11 9:02
 * @desc
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 * @version:
 */
public class MaximumSubarray_53 {
    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        int preSum = 0, result = nums[0];
        for (int num : nums) {
            //得到当前最大值
            preSum = Math.max(preSum + num, num);
            result = Math.max(preSum, result);
        }
        return result;
    }
}
