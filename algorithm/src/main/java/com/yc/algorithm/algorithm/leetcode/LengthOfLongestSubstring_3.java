package com.yc.algorithm.algorithm.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LengthOfLongestSubstring_3 {


    //""
    //"abcabcbb"
    //(abc)abcbb
    //a(bca)bcbb
    //ab(cab)cbb
    public int lengthOfLongestSubstring(String s) {
        //参数检查
        if (s == null || "".equals(s)) {
            return 0;
        }

        //思路分析
        //使用滑动窗口 + hash表来解决 【left,right】
        //如果不存在，则滑块窗口的右区间自增，结果长度也自增
        //如果存在，则滑动窗口左区间移动到重复位置后一个，右滑动窗口的位置不变
        int result = 0;
        Set<Character> existChar = new HashSet<>();
        int left = 0, right = -1;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i ++) {
            if ( i > 0) {
                //往后移动了，把前面一个删除掉
                existChar.remove(chars[i - 1]);
            }
            //如果不包含，则往后自增
            while (right + 1 < chars.length &&  !existChar.contains(chars[right + 1])) {
                existChar.add(chars[right + 1]);
                right ++;
            }
            //取最大值
            result = Math.max(result, right - i + 1);

        }
        return result;
    }
}
