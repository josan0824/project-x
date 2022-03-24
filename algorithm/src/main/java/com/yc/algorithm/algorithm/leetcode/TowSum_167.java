package com.yc.algorithm.algorithm.leetcode;

import java.util.Arrays;

/**
 * @author: josan_tang
 * @create_date: 2022/3/24 23:03
 * @desc: 求有序数组中的和为固定值，返回这个两个值的下标，从1开始
 * @version:
 */
public class TowSum_167 {

    public static void main(String[] args) {
        int[] numbers = {2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(numbers, target)));
    }


    public static int[] twoSum(int[] numbers, int target) {
        int l = 0; //表示左边的值
        int r = numbers.length - 1; //表示右边
        while(l < r) {
            if (numbers[l] + numbers[r] == target) {
                //如果左右相加正好等于target，则l+1、r+就是要找的值
                return new int[]{l+1, r+ 1};
            } else if (numbers[l] + numbers[r] > target) {
                //如果相加大于targer,则r--
                r--;
            } else {
                //如果相加大于targer,则l++
                l++;
            }
        }
        //没有找到则抛出错误
        return null;
    }
}
