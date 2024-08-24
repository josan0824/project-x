package com.yc.javabasic.test;


import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.Comparator;

/**************************************************
 * copyright (c) 2022
 * created by josan tang
 * date: 2023-08-01
 * description: 主要用于子市场和行政区的拼音比较，目前多音字是手动处理的
 **************************************************/
public class PinyinComparator implements Comparator<String> {

    public int compare(String o1, String o2) {

        for (int i = 0; i < o1.length() && i < o2.length(); i++) {

            int codePoint1 = o1.charAt(i);
            int codePoint2 = o2.charAt(i);

            if (Character.isSupplementaryCodePoint(codePoint1)
                    || Character.isSupplementaryCodePoint(codePoint2)) {
                i++;
            }

            if (codePoint1 != codePoint2) {
                if (Character.isSupplementaryCodePoint(codePoint1)
                        || Character.isSupplementaryCodePoint(codePoint2)) {
                    return codePoint1 - codePoint2;
                }

                String pinyin1 = pinyin((char) codePoint1);
                String pinyin2 = pinyin((char) codePoint2);

                if (pinyin1 != null && pinyin2 != null) { // 两个字符都是汉字
                    if (!pinyin1.equals(pinyin2)) {
                        return pinyin1.compareTo(pinyin2);
                    }
                } else {
                    return codePoint1 - codePoint2;
                }
            }
        }
        return o1.length() - o2.length();
    }

    /**
     * 字符的拼音，多音字就得到第一个拼音。不是汉字，就return null。
     */
    private String pinyin(char c) {
        String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c);
        if (pinyins == null) {
            return null;
        }
        // 特殊处理，长在地理位置中一般为长，如长宁
        if ("长".equals(String.valueOf(c))) {
            return "chang2";
        }
        return pinyins[0];
    }
}
