package com.yc.algorithm.algorithm.leetcode;

/**
 * @author: josan_tang
 * @create_date: 2022/7/19 17:12
 * @desc:给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * @version:
 */
public class MaxProfit_121 {

    /**
     * 1 <= prices.length <= 105
     * 0 <= prices[i] <= 104
     * @param prices [7, 1, 5, 3, 6, 4]
     * @return
     */
    public int maxProfit(int[] prices) {
        //检测参数
        int size = prices.length;
        if (size < 2) {
            return 0;
        }

        //分析思路
        //用max记录两个数值之前的最大差异，2次遍历数组
        int maxProfit = 0;
        //但是1 <= prices.length <= 10^5，n^2的时间复杂度只能接受10^4的数量级
/*
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                int distance = prices[j] - prices[i];
                if (distance > maxProfit) {
                    maxProfit = distance;
                }
            }
        }*/
        //用minPrice记录在当天之前的最低价，然后算出在当天理论上的最大利润，然后不断去取最大值
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) { //i = 2
            if (prices[i] <  minPrice) {
                minPrice = prices[i];   //minPrice = 1
            }
            int distance = prices[i] - minPrice;    //4
            if (distance > maxProfit) {
                maxProfit = distance;   //4
            }
        }
        return maxProfit;
    }
}
