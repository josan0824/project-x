package com.yc.algorithm.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author: josan_tang
 * @create_date: 2022/7/2 16:24
 * @desc:
 * @version:
 */
public class HWTest {
    /**
     * 输入包括两个正整数a,b(1 <= a, b <= 1000),输入数据包括多组。
     * 输出a+b的结果
     *
     * @param arr
     */
/*    public static void main(String[] arr) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.print(a + b);
        }
    }*/

    /**
     * 输入第一行包括一个数据组数t(1 <= t <= 100)
     * 接下来每行包括两个正整数a,b(1 <= a, b <= 1000)
     * 输出a+b的结果
     * @param arr
     */
/*    public static void main(String[] arr) {
        Scanner scanner = new Scanner(System.in);
        int lines = Integer.parseInt(scanner.nextLine());
        int[] sums = new int[lines];
        for (int i = 0; i < lines; i++) {
            String aAndB = scanner.nextLine();
            String[] aAndBArr =  aAndB.split(" ");
            sums[i] = Integer.parseInt(aAndBArr[0]) + Integer.parseInt(aAndBArr[1]);
        }

        for (int i = 0; i < sums.length; i++) {
            System.out.println(sums[i]);
        }
    }*/

    /**
     * 输入包括两个正整数a,b(1 <= a, b <= 10^9),输入数据有多组, 如果输入为0 0则结束输入
     * 输出a+b的结果
     * @param arr
     */
/*    public static void main(String[] arr) {
        Scanner sc = new Scanner(System.in);
        List<Integer> sumList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String aAndB = sc.nextLine();
            String[] aAndBArr = aAndB.split(" ");
            int a = Integer.parseInt(aAndBArr[0]);
            int b = Integer.parseInt(aAndBArr[1]);
            if (a == 0 && b == 0) {
                break;
            } else {
                sumList.add(a + b);
            }
        }

        for (Integer sum : sumList) {
            System.out.println(sum);
        }
    }*/

    /**
     * 输入数据包括多组。
     * 每组数据一行,每行的第一个整数为整数的个数n(1 <= n <= 100), n为0的时候结束输入。
     * 接下来n个正整数,即需要求和的每个正整数。
     *
     * 每组数据输出求和的结果
     * @param arr
     */
/*    public static void main(String[] arr) {
        Scanner sc = new Scanner(System.in);
        List<Integer> sumList = new ArrayList();

        while (sc.hasNextLine()) {
            String lineStr = sc.nextLine();
            String[] lineArr = lineStr.split(" ");
            //第一个数字
            int firstNum = Integer.parseInt(lineArr[0]);
            if (firstNum == 0) {
                //结束
                break;
            } else {
                //求后面的和
                int sum = 0;
                for (int i = 1; i < lineArr.length; i++) {
                    sum += Integer.parseInt(lineArr[i]);
                }
                sumList.add(sum);
            }
        }

        for (Integer sum : sumList) {
            System.out.println(sum);
        }
    }*/


    /**
     * 输入的第一行包括一个正整数t(1 <= t <= 100), 表示数据组数。
     * 接下来t行, 每行一组数据。
     * 每行的第一个整数为整数的个数n(1 <= n <= 100)。
     * 接下来n个正整数, 即需要求和的每个正整数。
     *
     * 每组数据输出求和的结果
     * @param arr
     */
/*    public static void main(String[] arr) {
        Scanner scanner = new Scanner(System.in);
        int lines = Integer.parseInt(scanner.nextLine());
        List<Integer> sumList = new ArrayList<>();
        for (int i = 0; i < lines; i++) {

            String aAndB = scanner.nextLine();
            String[] aAndBArr =  aAndB.split(" ");
            //求后面的和
            int sum = 0;
            for (int j = 1; j < aAndBArr.length; j++) {
                sum += Integer.parseInt(aAndBArr[j]);
            }
            sumList.add(sum);
        }

        for (Integer sum : sumList) {
            System.out.println(sum);
        }
    }*/


    /**
     * 输入数据有多组, 每行表示一组输入数据。
     * 每行的第一个整数为整数的个数n(1 <= n <= 100)。
     * 接下来n个正整数, 即需要求和的每个正整数。
     *
     * 每组数据输出求和的结果
     * @param arr
     */
/*    public static void main(String[] arr) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String aAndB = scanner.nextLine();
            String[] aAndBArr =  aAndB.split(" ");
            int first = Integer.parseInt(aAndBArr[0]);
            if (first == 0) {
                break;
            }
            //求后面的和
            int sum = 0;
            for (int j = 0; j < aAndBArr.length; j++) {
                sum += Integer.parseInt(aAndBArr[j]);
            }
            System.out.println(sum);
        }
    }*/

    /**
     * 输入数据有多组, 每行表示一组输入数据。
     * 每行不定有n个整数，空格隔开。(1 <= n <= 100)。
     *
     * 每组数据输出求和的结果
     */
/*    public static void main(String[] arr) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String aAndB = scanner.nextLine();
            String[] aAndBArr =  aAndB.split(" ");
            //求后面的和
            int sum = 0;
            for (int j = 0; j < aAndBArr.length; j++) {
                sum += Integer.parseInt(aAndBArr[j]);
            }
            System.out.println(sum);
        }
    }*/

    /**
     * 输入有两行，第一行n
     *
     * 第二行是n个字符串，字符串之间用空格隔开
     *
     * 返回排序后的字符串
     * @param args
     */
 /*   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());

        String inputStr = sc.nextLine();
        String[] inputArr = inputStr.split(" ");
        //对数组进行排序
        Arrays.sort(inputArr);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < inputArr.length - 1; i ++) {
            stringBuilder.append(inputArr[i]).append(" ");
        }
        stringBuilder.append(inputArr[inputArr.length - 1]);
        System.out.println(stringBuilder.toString());
    }*/


    /**
     * 多个测试用例，每个测试用例一行。
     *
     * 每行通过空格隔开，有n个字符，n＜100
     *
     * 对于每组测试用例，输出一行排序过的字符串，每个字符串通过空格隔开
     * @param args
     */
/*    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String orginStr = sc.nextLine();
            String[] inputArr = orginStr.split(" ");
            //对数组进行排序
            Arrays.sort(inputArr);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < inputArr.length - 1; i ++) {
                stringBuilder.append(inputArr[i]).append(" ");
            }
            stringBuilder.append(inputArr[inputArr.length - 1]);
            System.out.println(stringBuilder.toString());
        }
    }*/


    /**
     * 多个测试用例，每个测试用例一行。
     * 每行通过,隔开，有n个字符，n＜100
     *
     * 对于每组用例输出一行排序后的字符串，用','隔开，无结尾空格
     * @param args
     */
/*    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String orginStr = sc.nextLine();
            String[] inputArr = orginStr.split(",");
            //对数组进行排序
            Arrays.sort(inputArr);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < inputArr.length - 1; i ++) {
                stringBuilder.append(inputArr[i]).append(",");
            }
            stringBuilder.append(inputArr[inputArr.length - 1]);
            System.out.println(stringBuilder.toString());
        }
    }*/

    /**
     * 输入有多组测试用例，每组空格隔开两个整数
     *
     * 对于每组数据输出一行两个整数的和
     */
/*    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String orginStr = sc.nextLine();
            String[] inputArr = orginStr.split(" ");
            int a = Integer.parseInt(inputArr[0]);
            int b = Integer.parseInt(inputArr[1]);
            System.out.println(a + b);
        }
    }*/

    /**
     * 输入描述：
     * 第一行输入一个由字母和数字以及空格组成的字符串，第二行输入一个字符。
     *
     * 输出描述：
     * 输出输入字符串中含有该字符的个数。（不区分大小写字母）
     * @param arr
     */
    public static void main(String[] arr) {
        Scanner sc = new Scanner(System.in);
        String reline = sc.nextLine().toLowerCase();
        char chatAt = sc.nextLine().toLowerCase().charAt(0);
        char[] relineArr = reline.toCharArray();
        int result = 0;
        for (char c : relineArr) {
            if (chatAt == c) {
                result ++;
            }
        }
        System.out.println(result);
    }


}
