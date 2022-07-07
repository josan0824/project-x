package com.yc.algorithm.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: josan_tang
 * @create_date: 2022/3/24 23:03
 * @desc: 使用查找表求数组中两个元素的和为特定值
 * 所有元素放入查找表，之后对于每一个元素，查找target-a是否存在
 * @version:
 */
public class TowSum2_1 {

    public static void main(String[] args) {
        int[] numbers = {3,2,4};
        int target = 6;
        System.out.println(Arrays.toString(twoSum4(numbers, target)));
    }


    public static int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> record = new HashMap<>();
        for (int i = 0; i < numbers.length; i ++) {
            int complement = target - numbers[i];
            //判断需要的元素是否存在
            if (record.get(complement) != null) {
                int[] result = {record.get(complement), i};
                return result;
            }
            //已经遍历过的元素，放到查找表中，key为元素本身，value为下标
            record.put(numbers[i],  i);
        }
        //没有找到则抛出错误
        return null;
    }

    /**
     * 题目描述：在有序数组中找出两个数，使它们的和为 target。
     * 双指针的方式求和
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] numbers, int target) {
        int start = 0;
        int end = numbers.length - 1;
        int sum;
        while (start < end) {
            sum = numbers[start] + numbers[end];
            if (sum == target) {
                return new int[]{start, end};
            } else if (sum > target){
                end --;
            } else {
                start++;
            }
        }
        return null;
    }

    /**
     * 时间复杂度为n^2的方式
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum3(int[] nums, int target) {
        for(int i = 0; i < nums.length - 1; i++) {
            for(int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * 哈希表实现双数之和
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum4(int[] nums, int target) {
        //用一个哈希表存储遍历过的数据相关信息
        //key:nums[i],value:i
        Map<Integer, Integer> exist = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(exist.containsKey(target - nums[i])) {
                //如果存在，则找到目标,当前i其实为结果的第二个元素
                return new int[]{exist.get(target - nums[i]), i};
            }
            //如果不存在，则把当前的数据放到map中
            exist.put(nums[i], i);
        }
        return null;
    }
}
