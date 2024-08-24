package com.yc.javabasic.regularexpression;

import java.util.Arrays;
import java.util.Collections;

/**************************************************
 * copyright (c) 2022
 * created by josan tang
 * date:       
 * description: 
 **************************************************/
public class RegularExpressionTest {
    public static void main(String[] args) {
        String str = "[[116.4073044729697, 39.96861301654014], [116.38841220971013, 39.968209601976355], [116.38687003408128, 40.00214477527027], [116.40796445631797, 40.00252337805592], [116.4073044729697, 39.96861301654014]]";
        str = str.substring(2, str.length() - 2);
        int a = 0;
        int b = 5 / a;
        System.out.println(str);
        String[] strArr = str.split("],\\[|], \\[");

        for(int i =0; i <strArr.length; i++) {
            System.out.println(strArr[i]);
        }
        Arrays.asList(strArr);
    }



}
