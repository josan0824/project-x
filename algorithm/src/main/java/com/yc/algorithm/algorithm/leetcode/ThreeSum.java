package com.yc.algorithm.algorithm.leetcode;

import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: josan_tang
 * @create_date: 2022/7/6 12:29
 * @desc:
 * @version:
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] arr = {0,0,0,0};
        List<List<Integer>> result = threeSum2(arr);
        for (List<Integer> list : result) {
            System.out.println(list.toString());
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        //先进行排序
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        //枚举a
        for (int first = 0; first < n; first++) {
            if (first > 0 && nums[first] == nums[first-1]) {
                //如果和上次的数字一样，则跳过
                continue;
            }

            //c对应的指针初始值指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            //枚举b
            for (int second = first + 1; second < n; second ++) {
                if (second > first && nums[second] == nums[second - 1]) {
                    //如果和上次的数字一样，则跳过
                    continue;
                }
                //需要保证b的指针在c指针的左侧
                while(second < third && nums[second] + nums[third] > target) {
                    third--;
                }

                //如果b=c了，随着
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/3sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return 计算超出时间限制
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        //遍历a
        for (int i = 0; i < nums.length - 2; i ++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                //相同的数据，不需要重复处理
                continue;
            }
            int third = nums.length - 1;
            //遍历b
            for (int second = i + 1; second < nums.length - 1; second ++) {
                if (second > i + 1 && nums[second] == nums[second-1]) {
                    //相同的数据，不需要重复处理
                    continue;
                }
                int target = -(nums[i] + nums[second]);
                //当b小于c,且c位置的数字大于目标值时，循环让c不断减少
                //如果b不小于c,或者c的位置不大于目标值，循环结束，往后走，处理当前b没有合适的c进入下一轮b的判断；或者让b
                while (second < third && nums[third] > target) {
                    third --;
                }
                //如果遍历到b、c相等，则证明没有符合条件的数据
                if (second == third) {
                    break;
                }
                if (nums[i] + nums[second] + nums[third] == 0) {
                    List<Integer> tempList = new ArrayList<>();
                    tempList.add(nums[i]);
                    tempList.add(nums[second]);
                    tempList.add(nums[third]);
                    result.add(tempList);
                }
            }
        }
        return result;
    }
}
