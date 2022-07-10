package com.yc.algorithm.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ContainerWithTheMostWater_11 {
    public static void main(String[] args) {
        int[] height = new int[] {1, 8,6,2,5,4,8,3,7};
        System.out.println(maxArea2(height));
    }

    /**
     * 面积为 （j- i） * min(height[i], height[j])
     * 时间复杂度为O(n^2)，会超出时间限制
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int m = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int temp =  (j - i) * Math.min(height[j], height[i]);
                if (temp > m) {
                    m = temp;
                }
            }
        }
        return m;
    }

    /**
     * 面积为 （j- i） * min(height[i], height[j])
     * 时间复杂度为O(n^2)，会超出时间限制
     * @param height
     * @return
     */
    public static int maxArea2(int[] height) {
       int left = 0, rihgt = height.length - 1;

       int m = 0;
       while (left < rihgt) {
           //计算长方形面积
           int temp =  (rihgt - left) * Math.min(height[rihgt], height[left]);
           if (temp > m) {
               m = temp;
           }
           //移动数字较小的那个指针。
           //因为如果不移动教小的指针，下一次min(height[left], height(right))必然不会增加
           if (height[left] <= height[rihgt]) {
               left ++;
           } else {
               rihgt --;
           }
       }
       return m;
    }
}
