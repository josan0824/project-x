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
        BigDecimal one = new BigDecimal(100);
        BigDecimal tow = new BigDecimal(200);

        System.out.println(subtract(one, tow));

        BigDecimal three = new BigDecimal(10);
        BigDecimal four = new BigDecimal(3);
        System.out.println(divide(three, four));
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
