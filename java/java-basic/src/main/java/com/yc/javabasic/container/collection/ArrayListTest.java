package com.yc.javabasic.container.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class ArrayListTest {
    public static void main(String[] args) {

        List list = new ArrayList(10);
        for (int i = 0;i < 3; i++) {
            list.add(new Integer(1));
        }

        List l2 = new ArrayList<>();
        l2.add(null);
        l2.add(null);
        list.addAll(3, l2);

        System.out.println(list);
        list.remove(null);
        System.out.println(list);
    }

}
