package com.yc.algorithm.algorithm.leetcode;

public class HammingDistance_461 {
    public int hammingDistance(int x, int y) {
        //思路分析
        //位置不同可以使用异或位运算来算，最后结算结果中1的个数
        //二进制中有多少个1，可以使用Integer的bitCount函数来计算
        int result = x ^ y;
        return Integer.bitCount(result);
    }
}
