package com.yc.algorithm.algorithm.leetcode;

public class MaxProduct_152 {


    public int maxProduct(int[] nums) {
        //checkparams
        //如果只有一个元素，怎么去算子串？
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        //思路
        //使用动态规划
        // fmax（i） 表示到i位置的最大乘积， fmin(x)表示到i的最小乘积
        // 则fmax(i) = Math.max(fmax(i-1) * nums[i], fmin(i-1)*nums[i], nums[i])
        // 则fmin(i) = Math.min(fmax(i-1) * nums[i], fmin(i-1)*nums[i], nums[i])
        int[] fmaxArr = new int[n];
        int[] fminArr = new int[n];
        fmaxArr[0] = nums[0];
        fminArr[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < n; i ++) {
            fmaxArr[i] = Math.max(fmaxArr[i - 1] * nums[i], Math.max(fminArr[i - 1] * nums[i], nums[i]));
            fminArr[i] = Math.min(fmaxArr[i - 1] * nums[i], Math.min(fminArr[i - 1] * nums[i], nums[i]));
            if (fmaxArr[i] > result) {
                result = fmaxArr[i];
            }
        }
        return result;
    }
}
