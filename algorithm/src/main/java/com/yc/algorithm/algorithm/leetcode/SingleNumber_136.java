package com.yc.algorithm.algorithm.leetcode;

public class SingleNumber_136 {
    public int singleNumber(int[] nums) {
        //思路分析，如果用map去存元素出现的次数，空间复杂度会有问题
        //应该使用异或位运算来计算
        // 1. a^0 = a
        // 2.a ^ a = 0
        // 3. a ^ b ^ a = b ^ (a ^ a) = b ^ 0 = b
        //所以如果其他数都出现2次，只有一个数出现一次，那所有数异或，最后就是单出来的那个数
        int single = 0;
        for (int i = 0; i < nums.length; i ++) {
            single ^= nums[i];
        }
        return single;
    }
}
