package com.yc.algorithm.algorithm.leetcode;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 *  
 *
 *输入：target = 7, nums = [2,3,1,2,4,3]
 *输出：2
 *解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 */
public class MinSubArrayLen_209 {

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,2,4,3};
        int target = 7;
        System.out.println(minSubArrayLen(target, nums));
    }

    public static int minSubArrayLen(int target, int[] nums) {
        //[start,end]表示滑动窗口
        int start = 0;
        int end = 0;
        int sum = 0;
        //初始化结果为最大值，用于返回时判断是否有赋值，nums.length <= 10^5
        int ans = Integer.MAX_VALUE;
        //当end超出数组最大值时，停止
        while (end < nums.length) {
            //把滑动窗口的最右边的值，加入到和中
            sum += nums[end];
            //如果和大于等于目标值target，则需要判断是否需要记录下当前元素个数代替最小子数组；
            //并让和减去滑动窗口最左边的值，并让start++，直到sum小于target
            while (sum >= target) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start ++;
            }
            //往右边移动
            end ++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
