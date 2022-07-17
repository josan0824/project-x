package com.yc.algorithm.algorithm.leetcode;

/**
 * 回文字符串
 */
public class IsPalindrome_9 {

    public static void main(String[] args) {
        System.out.println(new IsPalindrome_9().isPalindrome(121));
    }

    public boolean isPalindrome(int x) {
        //分析过程
        //1. 如果是负数，一定不是回文
        //2. 如果是0，则是回文
        //3. 如果是正数，可以判断数字最前跟最后是否相等来判断是否为回文
        if (x < 0) {
            return false;
        } else if (x == 0) {
            return true;
        } else {
            String xStr =  String.valueOf(x);
            for (int i = 0; i < xStr.length() / 2; i++) {
                if (xStr.charAt(i) != xStr.charAt(xStr.length() - 1 -i)) {
                    return false;
                }
            }
        }
        return true;
    }
}
