package com.yc.algorithm.algorithm.leetcode;

/**
 * @author: josan_tang
 * @create_date: 2022/4/4 21:33
 * @desc:滑动窗口
 * @version:
 */
public class SlidingWindow_209 {
    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        int s = 7;
        System.out.println(minSubArrayLen(s, nums));
    }

    private static int minSubArrayLen(int s, int[] nums) {
        //nums[l... r]为我们的滑动窗口
        int l = 0;
        int r = -1;
        int sum = 0;
        int res = nums.length + 1;

        while (l < nums.length) {   //当左边的值小于数组长度，证明可以移动
            if (r + 1 < nums.length && sum < s) {
                //当和小于s时，滑动窗口的右边界往右边+1
                r++;
                sum += nums[r];
            } else {
                //当和大于s时，滑动窗口的左边界往右边+1
                sum -= nums[l];
                l++;
            }
            if (sum >= s) {
                //当和大于s,则当前为满足要求的滑动窗口
                res = Math.min(res, r - l + 1);
            }
        }
        if (res == nums.length + 1) {
            return 0;
        }
        return res;
    }

}
