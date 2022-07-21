package com.yc.algorithm.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: josan_tang
 * @create_date: 2022/7/18 18:06
 * @desc:反转链表 II
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *  
 * @version:
 */

class ListNode6 {
    int val;
    ListNode6 next;

    ListNode6() {
    }

    ListNode6(int val) {
        this.val = val;
    }

    ListNode6(int val, ListNode6 next) {
        this.val = val;
        this.next = next;
    }
}

public class ReverseBetween_92 {
    public ListNode6 reverseBetween(ListNode6 head, int left, int right) {
        //思路分析
        //先找到left、right对应的节点
        ListNode6 listNode = head;
        ListNode6 leftNode = null;
        ListNode6 rightNode = null;
        int count = 0;
        List<ListNode6> nodeList = new ArrayList<>();
        while (listNode != null) {
            if (count == left) {
                leftNode = listNode;
            } else if (count == right) {
                rightNode = listNode;
            }
            nodeList.add(listNode);
            listNode = listNode.next;
        }

        //然后left、right的节点值各自对调，并依次递增和减少，如果两个left>=right则结束
        while (left < right) {
            leftNode = nodeList.get(left-1);
            rightNode = nodeList.get(right-1);
            int temp = leftNode.val;
            leftNode.val = rightNode.val;
            rightNode.val = temp;
            left ++;
            right --;
        }
        return head;
    }
}
