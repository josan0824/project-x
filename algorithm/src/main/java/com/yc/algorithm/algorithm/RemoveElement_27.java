package com.yc.algorithm.algorithm;

import java.util.Arrays;

public class RemoveElement_27 {
    public static void main(String[] args) {
        int[] arr = {3,2,2,3};
        System.out.println(removeElement(arr, 3));
    }

    public static int removeElement(int[] nums, int key) {
        int k = 0; //k表示key个数
        //把元素移动到前面
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) {
                //如果当前key==num[i]
                k++;
            } else {
                nums[i - k] = nums[i];
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = nums[i];
        }

        System.out.println(Arrays.toString(result));
        return k;
    }

}
