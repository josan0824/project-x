package com.yc.algorithm.algorithm.leetcode;

/**
 * @author: josan_tang
 * @create_date: 2022/7/18 17:46
 * @desc: 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * @version:
 */
public class ClimbStairs_70 {


    //testcase  1 1
    //testcase 2 2
    //testcase 3 3
    //testcast 4
    public int climbStairs(int n) {
        //param check: 1 <= n <= 45 不需要校验

        //分析思路
        // 1. 使用f(x) 表示爬到x台阶所有的方法种类
        // 2. 初始化条件 f(1) = 1; f(2) = 2
        // 3. 当x > 2时，会发现，f(x)是f(x-1)和f(x-2)滚动过来的，到达f(x)的方式种类可以分为两类：
        // f(x-2) 即到达x-2之后再走两步到达x；或者f(x-1)即到达x-1再走一步到达x，则f(x) = f(x-2) + f(x-1)
        // 可以用动态规划来解决

        //处理初始值
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            //p、q、r表示连续的三步
            int p = 1,q = 2, r =0;
            for (int i = 3; i <= n; i++) {  //n = 4; i = 4
                r = p + q;  //r = 5
                p = q;      //p = 3
                q = r;      //q = 5
            }
            return r;
        }
    }
}
