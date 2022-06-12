package com.yc.javabasic.container.collection;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {
        CopyOnWriteArrayList collection = new CopyOnWriteArrayList();
        collection.add("1");

        collection.get(0);
    }
}
