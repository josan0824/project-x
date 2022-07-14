package com.yc.algorithm.algorithm.leetcode;

/**
 * @author: josan_tang
 * @create_date: 2022/7/14 14:28
 * @desc:给你一个链表的头节点 head ，判断链表中是否有环。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 *
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 * @version:
 */
public class CircularLinkedList_141 {


    class ListNode4 {
        int val;
        ListNode4 next;
        ListNode4(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 快慢指针
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode4 head) {
        if (head == null || head.next == null) {
            //数组是空或者只有一个元素，则不是环状
            return false;
        }
        //慢指针
        ListNode4 slow = head;
        //快指针
        ListNode4 fast = head.next;
        while (slow != fast) {
            //如果快指针结束了或者到了最后一个指针
            if (fast == null || fast.next == null) {
                return false;
            }
           slow = slow.next;
           fast = fast.next.next;
        }

        //如果在有环的时候，fast追上了slow
        return true;
    }
}
