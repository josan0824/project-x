package com.yc.algorithm.algorithm.leetcode;

import java.util.Stack;

/**
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 */

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

public class AddTwoNumbers_2 {
    public static void main(String[] args) {
        ListNode l12 = new ListNode(4);
        ListNode l11 = new ListNode(2, l12);

        ListNode l23 = new ListNode(9);
        ListNode l22 = new ListNode(6, l23);
        ListNode l21 = new ListNode(5, l22);

        new AddTwoNumbers_2().addTwoNumbers(l11, l21);
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //check params  题目已知，传入的链表不能为空

        //思路分析
        //针对两个链表长度相同的情况，两个链表对应位置的数字直接相加，满10进1,
        //针对两个链表不相同的情况，对短链表右侧位置补0，再进行上述操作
        ListNode head = null, tail = null;
        //表示进位
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            //进位数
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        //最后还有进位，则补一位
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }
}
