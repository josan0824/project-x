package com.yc.algorithm.algorithm.leetcode;

public class CountBits_338 {
    public int[] countBits(int n) {
        //思路分析
        //遍历n, 然后用Integer.bitCount来计算1的个数
        int[] ans = new int[n+1];
        for (int i = 0; i <= n; i ++) {
            ans[i] = Integer.bitCount(i);
        }
        return ans;
    }
}
