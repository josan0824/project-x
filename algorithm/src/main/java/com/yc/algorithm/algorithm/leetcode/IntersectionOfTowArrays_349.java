package com.yc.algorithm.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定两个数组nums,求两个数组的公共元素
 */
public class IntersectionOfTowArrays_349 {
    public static void main(String[] args) {
        int[] nums1 = {1,2,4,3,2};
        int[] nums2 = {2,3};
        System.out.println(intersectionOfTwoArray(nums1, nums2));
    }

    public static Set intersectionOfTwoArray(int[] nums1, int[] nums2) {
        Set result = new HashSet(); //结果用set来存储
        Set nums1Set = new HashSet();   //用于存储nums1中不重复的元素
        //先把muns1中的元素放入set中
        for (int i = 0; i< nums1.length; i++) {
            nums1Set.add(nums1[i]);
        }

        //遍历nums2，如果元素存在于nums1set中，则表示元素存在两个数组中，把该元素放入结果set中
        for (int i = 0; i < nums2.length; i ++) {
            if (nums1Set.contains(nums2[i])) {
                result.add(nums2[i]);
            }
        }
        return result;
    }
}
