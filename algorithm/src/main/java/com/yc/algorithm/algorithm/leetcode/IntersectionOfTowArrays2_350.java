package com.yc.algorithm.algorithm.leetcode;

import java.util.*;

/**
 * 给定两个数组nums,求两个数组的公共元素
 * 如果出现了多个一样的元素，则都需要输出
 */
public class IntersectionOfTowArrays2_350 {
    public static void main(String[] args) {
        int[] nums1 = {1,2,4,3,2};
        int[] nums2 = {2,3,2};
        System.out.println(intersectionOfTwoArray(nums1, nums2));
    }

    public static List intersectionOfTwoArray(int[] nums1, int[] nums2) {
        List result = new ArrayList(); //结果用set来存储
        Map<Integer, Integer> nums1Map = new HashMap();   //用于存储nums1中元素出现的频次
        //先把nuns1中的元素放入map中
        for (int i = 0; i < nums1.length; i++) {
            if (nums1Map.containsKey(nums1[i])) {
                nums1Map.put(nums1[i], nums1Map.get(nums1[i]).intValue() + 1);
            } else {
                nums1Map.put(nums1[i], 1);
            }
        }

        //遍历nums2，如果元素存在于nums1set中，则表示元素存在两个数组中，把该元素放入结果list中,并把map中的频次减1
        for (int i = 0; i < nums2.length; i ++) {
            if (nums1Map.get(nums2[i]) != null && nums1Map.get(nums2[i])  > 0) {
                result.add(nums2[i]);
                nums1Map.put(nums2[i], nums1Map.get(nums2[i]) - 1);
            }
        }
        return result;
    }
}
