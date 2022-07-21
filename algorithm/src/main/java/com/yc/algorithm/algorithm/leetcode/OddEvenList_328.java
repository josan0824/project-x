package com.yc.algorithm.algorithm.leetcode;

/**
 * @author: josan_tang
 * @create_date: 2022/7/14 15:20
 * @desc:给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
 * <p>
 * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
 * <p>
 * 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
 * @version:
 */

 class ListNode5 {
    int val;
    ListNode5 next;

    ListNode5() {
    }

    ListNode5(int val) {
        this.val = val;
    }

    ListNode5(int val, ListNode5 next) {
        this.val = val;
        this.next = next;
    }
}

public class OddEvenList_328 {
    public ListNode5 oddEvenList(ListNode5 head) {
        if (head == null) {
            return head;
        }
        //偶节点头部
        ListNode5 evenHead = head.next;
        ListNode5 odd = head;
        ListNode5 even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        //让奇节点的尾部指向偶节点的头部
        odd.next = evenHead;
        return head;
    }
}