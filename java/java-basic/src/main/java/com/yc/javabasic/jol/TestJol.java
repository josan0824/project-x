package com.yc.javabasic.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author: josan_tang
 * @create_date: 2022/5/19 11:20
 * @desc:
 * @version:
 *  java.lang.Integer object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *  0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
 *  4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *  8     4        (object header)                           86 d3 05 00 (10000110 11010011 00000101 00000000) (381830)
 *  12     4    int Integer.value                             0
 *  Instance size: 16 bytes
 */
public class TestJol {
    public static void main(String[] args) {
        int a = 0;
        //计算对象的大小（单位为字节）
        System.out.println(ClassLayout.parseInstance(a).instanceSize());

        //查看独享的内存布局
        System.out.println(ClassLayout.parseInstance(a).toPrintable());


    }
}
