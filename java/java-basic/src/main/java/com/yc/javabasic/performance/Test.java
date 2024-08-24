package com.yc.javabasic.performance;

/**************************************************
 * copyright (c) 2022
 * created by josan tang
 * date:       
 * description: 
 **************************************************/
public class Test {

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        int[][] arr = new int[10000][10000];
        long start = System.currentTimeMillis();
        for (int i= 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++) {
                // TODO 关注点
                arr[i][j] = i + j;
            }
        }
        System.out.println("test1:" + (System.currentTimeMillis() - start) + "ms");
    }

    private static void test2() {
        int[][] arr = new int[10000][10000];
        long start = System.currentTimeMillis();
        for (int i= 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++) {
                // TODO 关注点
                arr[j][i] = i + j;
            }
        }
        System.out.println("test2:" + (System.currentTimeMillis() - start) + "ms");
    }
}
