package com.yc.algorithm.algorithm.leetcode;

import io.swagger.models.auth.In;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.time.Clock.system;

/**
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 *  
 *
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 *
 */
public class MajorityElement_169 {

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,3};
        System.out.println(new MajorityElement_169().majorityElement(nums));
    }

    /**
     * 使用Map存储元素个数
     * 时间负责度:O(n)
     * 空间复杂度:O(n)
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        //参数检查
        //1 <= n <= 5 * 104
        int n = nums.length;    //3
        if (n  == 1) {
            return nums[0];
        }

        //思路分析
        // 使用Map存储元素出现的次数，如果大于n / 2，则返回
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            int value = nums[i];    //3
            if (countMap.containsKey(value)) {
                //存在则更新
                int count = countMap.get(value);
                count++;
                if (count > n / 2) {
                    return value;
                }
                countMap.put(value, count);
            } else {
                //不存在则新增
                countMap.put(value, 1);
            }
        }
        //如果没有找到数据返回0
        return 0;
    }

    /**
     * 时间负责度:O(nlogn)
     * 空间复杂度:O(1)
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        //思路分析
        //比如排序好的数组{0，....,n/2,....n-1},如果一个数出现的次数> n/2个，那么这个数必然会在n/2位置
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
