package com.yc.algorithm.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: josan_tang
 * @create_date: 2022/3/24 23:03
 * @desc: 给出4个数组，寻找有多少个i、j、k、l组合
 * 可以使得A[i] + B[j] + C[k] + D[l] = 0，ABCD拥有相同元素个数N
 * 且0 <= N<= 500
 * 暴力解法是N^4,最多600多亿
 * 如果把其他一个放入查找表，N^3,最多为1亿多
 * 上述两种方式，都不能在1s计算出来
 * 需要考虑把AB和放入到查找表，从而编程N^2
 * @version:
 */
public class ForSumCount_454 {

    public static void main(String[] args) {
        int[] numbers1 = {1};
        int[] numbers2 = {1};
        int[] numbers3 = {-1};
        int[] numbers4 = {-1};
        System.out.println(forSumCount(numbers1, numbers2, numbers3, numbers4));
    }


    public static int forSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int result = 0;
        //记录AB合出现的频率
        Map<Integer, Integer> record = new HashMap<>();
        for (int i = 0; i< nums1.length; i ++) {
            for (int j = 0; j < nums2.length;j++) {
                if (record.containsKey(nums1[i]+ nums2[j])) {
                    record.put(nums1[i]+ nums2[j], record.get(nums1[i]+ nums2[j]) + 1);
                } else {
                    record.put(nums1[i]+ nums2[j], 1);
                }
            }
        }
        //看-C-D的数据是否存在记录表中
        for (int i = 0; i< nums3.length; i ++) {
            for (int j = 0; j < nums4.length;j++) {
                if (record.containsKey(-nums3[i]-nums4[j])) {
                    result += record.get(-nums3[i]-nums4[j]);
                }
            }
        }
        return result;
    }
}
