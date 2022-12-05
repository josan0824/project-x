package com.yc.javabasic.datatype;

import java.math.BigDecimal;

/**************************************************
 * copyright (c) 2022
 * created by josan tang
 * date:       
 * description: 
 **************************************************/
public class BigDecimalTest {
    public static void main(String[] args) {
/*        BigDecimal one = new BigDecimal(100);
        BigDecimal tow = new BigDecimal(200);

        System.out.println(subtract(one, tow));

        BigDecimal three = new BigDecimal(10);
        BigDecimal four = new BigDecimal(3);
        System.out.println(divide(three, four));*/
        //尽量使用参数类型为String的构造参数
        BigDecimal a = new BigDecimal(0.1);
        System.out.println("a values is :" + a);
        BigDecimal a2 = new BigDecimal("0.1");
        System.out.println("a2 values is :" + a2);

        //通过BigDecimal的divide方法进行除法时当不整除，出现无限循环小数时
        //Exception in thread "main" java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
        BigDecimal d1 = new BigDecimal(1);
        BigDecimal d2 = new BigDecimal(3);
        //System.out.println(d1.divide(d2));
        //需要像下面这样指定精确的小数点
        System.out.println(d1.divide(d2, 2, 1));

    }

    /**
     * BigDecimal减
     * @param one
     * @param tow
     * @return
     */
    private static BigDecimal subtract(BigDecimal one, BigDecimal tow) {
        return one.subtract(tow);
    }

    /**
     * BigDecimal除
     * @param one
     * @param tow
     * @return
     */
    private static BigDecimal divide(BigDecimal one, BigDecimal tow) {
        return one.divide(tow);
    }
}
