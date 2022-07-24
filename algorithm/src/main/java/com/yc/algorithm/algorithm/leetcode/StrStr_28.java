package com.yc.algorithm.algorithm.leetcode;

public class StrStr_28 {

    public static void main(String[] args) {
        String haystack = "a";
        String needle = "a";
        new StrStr_28().strStr(haystack, needle);
    }


    //haystack = "a", needle = "a"
    public int strStr(String haystack, String needle) {
        //参数检查
        if ("".equals(needle)) {
            return 0;
        }

        //实现思路
        //找到第一个相等的元素，则继续往后排查needle个元素是否相等，如果相等，则返回i
        int needleLength = needle.length(); //2

        for (int i = 0; i <= haystack.length() - needleLength; i++) {    //i<3 i = 2
            char curChat = haystack.charAt(i);  //l
            if (curChat == needle.charAt(0)) {
                //如果第一位相同，则继续往后找
                //记录第一个相同后，后续的相同个数
                int equalCount = 0;
                for (int j = 1; j < needle.length(); j++) { //j=1
                    char hayChar = haystack.charAt(i + j);  //l
                    char needleChar = needle.charAt(j); //l
                    if (hayChar == needleChar) {
                        equalCount ++;      //1
                    }
                }
                if (equalCount == needleLength - 1) {
                    return i;
                }
            }
        }
        return -1;
    }
}
