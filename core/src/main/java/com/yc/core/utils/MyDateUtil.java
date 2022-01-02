package com.yc.core.utils;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author: josan_tang
 * @create_date: 2022/1/2 9:43
 * @desc:
 * @version:
 */
public class MyDateUtil {

    /**
     * DateTime转String（年月日时分秒）
     * @param date
     * @return 2022-01-02 10:54:53
     */
    public static String toDatetimeStr(Date date) {
        return DateUtil.formatDateTime(date);
    }

    /**
     * DateTime转String（年月日）
     * @param date
     * @return 2022-01-02
     */
    public static String toDateStr(Date date) {
        return DateUtil.formatDate(date);
    }

    public static void main(String[] args) {
        //转年月日时分秒String
        //System.out.println(toDatetimeStr(new Date()));

        //转年月日String
        System.out.println(toDateStr(new Date()));
    }
}
