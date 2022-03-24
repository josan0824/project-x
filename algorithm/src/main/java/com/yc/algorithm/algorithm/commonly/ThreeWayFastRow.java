package com.yc.algorithm.algorithm.commonly;

import java.util.Arrays;

/**
 * @author: josan_tang
 * @create_date: 2022/3/24 9:15
 * @desc: 三路快排
 * @version:
 */
public class ThreeWayFastRow {

    public static void main(String[] args) {
        int[] nums = {0,1,2,1,0,1,0,2,1};
        threeWayFastRow(nums);
    }

    /**
     * 三路快排
     * 拿元素和1比较，如果
     * 1. 等于1，则位置不需要动,i++
     * 2. 如果小于1，则zero++, 把i和zero交换位置，i++
     * 3. 如果大于1，则tow--, 并把tow和i交换位置，i++
     * @param nums
     */
    public static void threeWayFastRow(int nums[]) {
        //[0,0,1,1,1,2,2,2,2]
        int zero = -1;  //zero表示0结束的位置，从[0, zero] 表示0的区间,开始没有，zero为-1
        int tow = nums.length;    //tow表示2开始的位置，从[tow, nums.length - 1]表示2的区间，开始没有，则tow = nums.length
        int key = 1;

        for (int i = 0; i < tow; i++) {
            if (nums[i] == key) {
                //等于1
            } else if (nums[i] < key) {
                //小于1
                zero ++;
                int temp = nums[zero];
                nums[zero] = nums[i];
                nums[i] = temp;
            } else {
                //大于1
                if (nums[i] > 2) {
                    System.out.println("非法数字，不能大于2");
                }
                tow --;
                int temp = nums[tow];
                nums[tow] = nums[i];
                nums[i] = temp;
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
