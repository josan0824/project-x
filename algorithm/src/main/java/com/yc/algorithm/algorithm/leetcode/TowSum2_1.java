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
        int[] numbers = {1,2,2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(numbers, target)));
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
}
