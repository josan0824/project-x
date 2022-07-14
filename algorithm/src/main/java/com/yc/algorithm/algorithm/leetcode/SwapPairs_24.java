package com.yc.algorithm.algorithm.leetcode;

/**
 * @author: josan_tang
 * @create_date: 2022/7/14 14:58
 * @desc:给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * @version:
 */
public class SwapPairs_24 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        //如果移动节点后面还有2个及以上元素，交换最近两个元素
        while (dummy.next != null && dummy.next.next != null) {
            ListNode firstNode = dummy.next;
            ListNode secondNode = dummy.next.next;
            //交换1、2的数据
            int temp = secondNode.val;
            secondNode.val = firstNode.val;
            firstNode.val = temp;
            dummy = secondNode;
        }
        return head;
    }
}
