package com.yc.algorithm.algorithm.leetcode;

/**
 * @author: josan_tang
 * @create_date: 2022/4/4 21:33
 * @desc:滑动窗口中做记录
 * @version:
 */
public class SlidingWindow2_3 {
    public static void main(String[] args) {
        String s = "bbbbb";
        System.out.println(lengthOfLongstSubString(s));
    }

    private static int lengthOfLongstSubString(String s) {
        //记录出现的次数
        int[] freq = new int[256];
        //滑动窗口s[l...r]
        int l = 0;
        int r = -1;
        int res = 0;
        while (l < s.length()) {
            if (r + 1 < s.length() && freq[(int) (s.charAt(r + 1))] == 0) {
                r++;
                freq[(int) (s.charAt(r))]++;
            } else {
                freq[(int) (s.charAt(l))]--;
                l++;
            }
            res = Math.max(res, r - l + 1);
        }

        return res;
    }

}
