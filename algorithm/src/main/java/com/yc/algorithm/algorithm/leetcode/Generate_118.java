package com.yc.algorithm.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Generate_118 {
    public static void main(String[] args) {
        new Generate_118().generate(5);
    }

    List<List<Integer>> reuslt = new ArrayList<>();

    public List<List<Integer>> generate(int numRows) {
        //参数检查

        //思路分析
        //第i行的第j个元素满足，
        //j = 0或者j=i-1,则为1；否则为(i-1, j-1) + (i - 1, j)

        //结果
        for (int i= 1; i <= numRows; i++) {
            //得到每一行的数据
            reuslt.add(getGenerate(i));
        }
        return reuslt;
    }

    /**
     * 得到第i行的元素(i从1开始，到nowRows结束)
     * @param nowRows
     * @return
     */
    public List<Integer> getGenerate(int nowRows) {
        List<Integer> rowList = new ArrayList<>();
        for (int i = 1; i <= nowRows; i++) {
            if (i == 1 || i == nowRows) {
                rowList.add(1);
            } else {
                int left = reuslt.get(nowRows-2).get(i - 2);
                int right = reuslt.get(nowRows-2).get(i - 1);
                rowList.add(left + right);
            }
        }
        return rowList;
    }


}
