package com.yc.algorithm.algorithm.leetcode;

import java.util.Stack;

/**
 * 有效括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidParentheses {

    private static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) {
        String s = "{[]}";
        System.out.println(isValid(s));
    }

    /**
     * 判断是否为有效括号
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        for (char c : s.toCharArray()) {
            if ("(".equals(String.valueOf(c))
                || "[".equals(String.valueOf(c))
                || "{".equals(String.valueOf(c))) {
                //如果是左括号，则压入栈
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    //如果出现是右括号时，栈里是空，即没有匹配的左括号，则为无效括号
                    return false;
                }
                if (")".equals(String.valueOf(c))){
                    //如果是右括号，则取出最近的左括号，判断是否匹配，如果匹配不上，则无效
                    char lastChar = stack.pop();
                    if (!"(".equals(String.valueOf(lastChar))) {
                        return false;
                    }
                } else if ("]".equals(String.valueOf(c))) {
                    char lastChar = stack.pop();
                    if (!"[".equals(String.valueOf(lastChar))) {
                        return false;
                    }
                } else if ("}".equals(String.valueOf(c))) {
                    char lastChar = stack.pop();
                    if (!"{".equals(String.valueOf(lastChar))) {
                        return false;
                    }
                }
            }
        }
        if (!stack.isEmpty()) {
            //如果最后栈里还有左括号，则无效
            return false;
        }
        return true;
    }
}
