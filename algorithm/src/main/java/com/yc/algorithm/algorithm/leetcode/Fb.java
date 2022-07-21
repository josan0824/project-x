package com.yc.algorithm.algorithm.leetcode;

import io.swagger.models.auth.In;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author: josan_tang
 * @create_date: 2022/7/14 17:06
 * @desc:
 * @version:
 */
public class Fb {

    static HashMap<Integer, BigInteger> hashmap = new HashMap<>();

    //{1,1,2,3,5,8}
    //n
    //输出0-n
    public static void main(String[] args) {
        System.out.println(Arrays.toString(getArr(100)));

    }

    //testcase 0 null
    //testcase 2
    private static long[] getArr(int n) {
        //参数考虑
        if (n <= 0) {
            return null;
        }
        //定义返回结果
        long[] ans = new long[n + 1];
        for (int i = 0; i < n + 1; i++) {
            ans[i] = fb(i);
            hashmap.put(i, ans[i]);
        }

        //返回结果
        return ans;
    }

    /**
     * 得到下标为i的值
     *
     * @param i
     * @return
     */
    private static long fb(int i) {
        //处理第一个元素
        if (i == 0 || i == 1) {
            return 1;
        }
        if (hashmap.containsKey(i)) {
            return hashmap.get(i);
        }
        return fb(i - 1) + fb(i - 2);
    }
}
