package com.yc.algorithm.algorithm.leetcode;

public class PlusOne_66 {
    public int[] plusOne(int[] digits) {
        //参数检查

        //思路分析
        //倒叙遍历，如果加1之后，大于等于10了，则需要进位,默认开始就要进位
        int jinwei = 1;
        for (int i = digits.length  - 1; i >= 0; i--) {
            int newNum = digits[i] + jinwei;
            digits[i] = newNum % 10;
            jinwei = newNum / 10;
        }
        //如果第一位需要进位，则需要最前面补1
        if (jinwei > 0) {
            int[] newDigits = new int[digits.length + 1];
            //进一位后，肯定为1
            newDigits[0] = 1;
            for (int i = 1; i < newDigits.length - 1; i++) {
                newDigits[i] = digits[i - 1];
            }
            return newDigits;
        }
        return digits;
    }
}
