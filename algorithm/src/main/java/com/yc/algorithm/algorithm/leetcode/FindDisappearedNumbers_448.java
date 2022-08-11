package com.yc.algorithm.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class FindDisappearedNumbers_448 {

    //[4,3,2,7,8,2,3,1]
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //思路分析
        //为了让空间复杂度为1，需要原地修改
        //遍历nums，让每个nums[x-1]+n,这样就可以让对应位置的值大于n了，但是遍历到该位置时，需要对n做下取余
        //最后再遍历nums,如果小于n的位置，就是没有出现过的
        int n = nums.length;
        for (int i = 0 ; i < n ; i++) {
            int x = (nums[i] - 1) % n;
            nums[x] += n;   //[12,19,10,23,8,10,3,9]
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0 ; i < n ; i++) {
            if (nums[i] <= n) {
                result.add(i + 1);
            }
        }
        return result;
    }
}
