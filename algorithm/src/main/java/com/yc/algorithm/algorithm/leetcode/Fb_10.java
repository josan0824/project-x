package com.yc.algorithm.algorithm.leetcode;

/**
 * 斐波拉契数列
 */
public class Fb_10 {
    public static void main(String[] args) {
        System.out.println(new Fb_10().fib(2));
    }

    public int fib(int n) {
        int MOD = 1000000007;
        //参数校验
        if (n < 2) {
            return n;
        }

        //使用动态规划求解
        int p = 0, q = 1, r = 1;
        for (int i = 1; i <= n; i++) {  //i=1; p = 1;q = 1;r = 2;    i = 2，p = 1;q = 2;r = 3
            p = q;
            q = r;
            r = (p + q) % MOD;
        }

        return r;
    }
}
