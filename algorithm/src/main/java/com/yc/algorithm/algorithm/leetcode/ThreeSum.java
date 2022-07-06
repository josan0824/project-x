package com.yc.algorithm.algorithm.leetcode;

import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: josan_tang
 * @create_date: 2022/7/6 12:29
 * @desc:
 * @version:
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] arr = {1,2,3,-3,-2,-1};
        List<List<Integer>> result = threeSum(arr);
        for (List<Integer> list : result) {
            System.out.println(list.toString());
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        //先进行排序
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        //枚举a
        for (int first = 0; first < n; first++) {
            if (first > 0 && nums[first] == nums[first-1]) {
                //如果和上次的数字一样，则跳过
                continue;
            }

            //c对应的指针初始值指向数组的最右端
            int third = n - 1;
            int target = -nums[first]; //???
            //枚举b
            for (int second = first + 1; second < n; second ++) {
                if (second > first && nums[second] == nums[second - 1]) {
                    //如果和上次的数字一样，则跳过
                    continue;
                }
                //需要保证b的指针在c指针的左侧
                while(second < third && nums[second] + nums[third] > target) {
                    third--;
                }

                //如果b=c了，随着
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

}
