package com.yc.algorithm.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortList_148 {
    public static void main(String[] args) {
        ListNode list4 = new ListNode(4);
        ListNode list3= new ListNode(1, list4);
        ListNode list2= new ListNode(2, list3);
        ListNode list1= new ListNode(4, list2);
        new SortList_148().sortList(list1);
    }

    public ListNode sortList(ListNode head) {
        //链表生成集合
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        //排序
        Collections.sort(list);

        //排序以后，构造排序链表
        ListNode nextNode = null;
        for (int i = list.size() - 1; i >= 0; i -- ) {
            ListNode listNode = new ListNode();
            listNode.val = list.get(i);
            listNode.next = nextNode;
            nextNode = listNode;
        }
        return nextNode;
    }
}
