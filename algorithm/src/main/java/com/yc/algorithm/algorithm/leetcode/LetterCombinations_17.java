package com.yc.algorithm.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations_17 {
    public static void main(String[] args) {
        new LetterCombinations_17().letterCombinations("23");
    }

    public List<String> letterCombinations(String digits) {
        //check params
        List<String> result = new ArrayList<>();
        if (digits == null || "".equals(digits)) {
            return result;
        }

        //思路分析
        //1. 使用map存储对应关系，key为数字2~9，value数字对应的字母字符串
        Map<Character, String> digitsMap = new HashMap<>();
        digitsMap.put('2', "abc");
        digitsMap.put('3', "def");
        digitsMap.put('4', "ghi");
        digitsMap.put('5', "jkl");
        digitsMap.put('6', "mno");
        digitsMap.put('7', "pqrs");
        digitsMap.put('8', "tuv");
        digitsMap.put('9', "wxyz");
        //2. 遍digits及数字上的对应字母列表，找到所有组合
        backtrack(result, digitsMap, digits, 0, new StringBuffer());
        return result;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index,StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letter = phoneMap.get(digit);
            int lettersCount = letter.length();
            for (int i = 0; i <lettersCount; i ++) {
                combination.append(letter.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }

        }

    }
}
