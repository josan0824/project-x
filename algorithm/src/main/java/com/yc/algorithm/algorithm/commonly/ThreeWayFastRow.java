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
        int[] nums = {2,1,2,1,0,1,1,2,1};
        //threeWayFastRow(nums);
        sort2(nums);
    }

    /**
     * 三路快排
     * 拿元素和1比较，如果
     * 1. 等于1，则位置不需要动,i++
     * 2. 如果小于1，则zero++, 把i和zero交换位置，i++
     * 3. 如果大于1，则tow--, 并把tow和i交换位置，i不变
     * @param nums
     */
    public static void threeWayFastRow(int nums[]) {
        //[0,0,1,1,1,2,2,2,2]
        int zero = -1;  //zero表示0结束的位置，从[0, zero] 表示0的区间,开始没有，zero为-1
        int tow = nums.length;    //tow表示2开始的位置，从[tow, nums.length - 1]表示2的区间，开始没有，则tow = nums.length
        int key = 1;

        for (int i = 0; i < tow; ) {
            if (nums[i] == key) {
                //等于1
                i++;
            } else if (nums[i] < key) {
                //小于1
                zero ++;
                int temp = nums[zero];
                nums[zero] = nums[i];
                nums[i] = temp;
                i++;
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

    public static void sort2(int[] arr) {
        int zero = -1;  //[0，zero] 表示0的区间，默认-1
        int two = arr.length;   //[two, arr.length - 1]表示2的区间,默认arr.length

        //如果找到到了tow的位置，右边都是2，则不需要继续排序了，则终止
        for (int i = 0 ; i < two;) {
            if (arr[i] == 1) {
                //如果为1，不用交换数据，i++
                i++;
            } else if (arr[i] < 1) {
                //如果<1,则放左边,交换的肯定是arr[i]=1，所以i++
                zero ++;
                int temp = arr[zero];
                arr[zero] = arr[i];
                arr[i] = temp;
            } else if (arr[i] > 1) {
                //如果>1,则放右边,交换的arr[i]不一定为0或者1，所以i不需要自增，需要继续判断i
                two --;
                int temp = arr[two];
                arr[two] = arr[i];
                arr[i] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

}
