package com.yc.algorithm.algorithm.commonly;

/**
 * 递归
 */
public class Recurrence {
    public static void main(String[] args) {
        System.out.println(f(10));
    }

    /**
     * 递归
     * @param n
     * @return
     */
    private static int f(int n) {
        //终止条件
        if (n == 1) {
            return 1;
        }
        //递推公式
        return f(n - 1) + 1;
    }

}
