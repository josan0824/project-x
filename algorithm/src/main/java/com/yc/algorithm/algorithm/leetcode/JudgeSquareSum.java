package com.yc.algorithm.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: josan_tang
 * @create_date: 2022/3/24 23:03
 * @desc: 题目描述：判断一个非负整数是否为两个整数的平方和。
 * @version:
 */
public class JudgeSquareSum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(judgeSquareSum(5)));
    }

    public static int[] judgeSquareSum(int target) {
        int i = 0;
        int j = (int) Math.sqrt(target);
        int sum;
        while (i < j) {
            sum = i * i + j * j;
            if (sum == target) {
                return new int[]{i, j};
            } else if (sum > target){
                j --;
            } else {
                i++;
            }
        }
        return null;
    }


}
