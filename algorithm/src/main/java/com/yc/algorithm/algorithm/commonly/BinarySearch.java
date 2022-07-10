package com.yc.algorithm.algorithm.commonly;

/**
 * @author: josan_tang
 * @create_date: 2022/3/21 15:27
 * @desc: 二分查找
 * @version:
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20%E9%A2%98%E8%A7%A3%20-%20%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE.md
 */
public class BinarySearch {

    /*
    Input : [1,2,3,4,5]
    key : 3
    return the index : 2*/

    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("i:" + i +  ",index:" + binarySearchByRecursion(arr, i));
        }
    }

    /**
     * 二分查找
     * @param args
     * @param key
     * @return
     */
    public static int binarySearch(int[] args, int key) {
        //left表示数组的左边，right表示右边 [left, right]
        int left = 0;
        int right = args.length - 1;
        int mid = left + (right - left) / 2;    //避免整型溢出

        //如果left <= right了，[left, right]这样的区间都有意义，则查询值
        while (left <= right) {
            if (args[mid] == key) { //如果中间值就是key,则直接放回mid
                return mid;
            } else if (key > args[mid]) {   //如果key大于中间值，则表示在(mid, right]中，则left = mid + 1
                left = mid + 1;
                mid = left + (right - left) / 2;
            }else {   //如果key小于中间值，则表示在[left, mid) 中，则right = mid - 1
                right = mid - 1;
                mid = left + (right - left) / 2;
            }
        }
        return -1;
    }

    /**
     * 通过二分查找key
     * @param arr 查找数据的数组
     * @param key 需要查找的key
     * @return key 所在的位置；如果未查找到，返回-1
     */
    public static int binarySearchNew(int[] arr, int key) {
        //left左边界，right表示数组的右边，[left, right]
        int left = 0;
        int right = arr.length - 1;
        //防止整形溢出
        int mid = left + (right - left) / 2;
        while (left <= right) {
            if (arr[mid] == key) {
                //找到数据，直接返回
                return mid;
            } else if (arr[mid] < key) {
                left = mid + 1;
                mid = left + (right - left) / 2;
            } else {
                right = mid - 1;
                mid = left + (right - left) / 2;
            }
        }
        return -1;
    }

    /**
     * 使用递归来实现二分查找
     * @param arr
     * @param key
     * @return
     */
    public static int binarySearchByRecursion(int[] arr, int key)  {
        return binarySearchPart(arr, 0, arr.length - 1, key);
    }

    /**
     * 针对数组的部分进行查找
     * @param arr
     * @param left
     * @param right
     * @param key
     * @return
     */
    public static int binarySearchPart (int[] arr, int left, int right,int key) {
        //当左边大于右边时，表示没有找到合适的元素
        if (left > right) {
            return -1;
        }
        int mid = (right - left) / 2 + left;
        if (arr[mid] == key) {
            return mid;
        } else if (arr[mid] > key) {
            return binarySearchPart(arr, left, mid - 1, key);
        } else {
            return binarySearchPart(arr, mid + 1, right, key);
        }
    }

}
