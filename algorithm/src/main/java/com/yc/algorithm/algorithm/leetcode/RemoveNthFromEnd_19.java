package com.yc.algorithm.algorithm.leetcode;

/**
 * @author: josan_tang
 * @create_date: 2022/7/14 14:40
 * @desc: 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * @version:
 */
public class RemoveNthFromEnd_19 {

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 双指针来实现
     *
     * @param head
     * @param n
     * @return   [1]  1
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode vListNode = new ListNode(0, head);
        //first表示在前面的指针，second表示在后面的指针，从虚拟开始，中间间隔n个
        ListNode first = head;
        ListNode second = vListNode;
        for (int i = 0; i < n; i++) {
            //让first先到指定位置
            first = first.next;
        }
        while (first != null) {
            //都往后移动
            first = first.next;
            second = second.next;
        }
        //如果first.next == null，表示链表已经到了最后一个元素，这个时候second + 1就是要删除的数据
        second.next = second.next.next;
        return vListNode.next;
    }
}
