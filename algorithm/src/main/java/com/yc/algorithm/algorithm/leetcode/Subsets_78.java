package com.yc.algorithm.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 */
public class Subsets_78 {
    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3};
        System.out.println(subsets(arr));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //元素个数
        int n = nums.length;
        //1 << n表示 2^n次方
        for (int i = 0; i < (1 << n); i ++) {
           List<Integer> tempList = new ArrayList<>();
           for (int j = 0; j < n; j++) {
               //看当前位是否应该取数
               if ((i & (1 << j)) != 0) {
                   tempList.add(nums[j]);
               }
           }
           result.add(tempList);
         }
        return result;
    }
}
