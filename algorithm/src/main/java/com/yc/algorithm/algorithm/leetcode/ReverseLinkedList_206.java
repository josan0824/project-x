package com.yc.algorithm.algorithm.leetcode;


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
 * 链表反转
 */
class ReverseLinkedList_206 {
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4,node5);
        ListNode node3 = new ListNode(3,node4);
        ListNode node2 = new ListNode(2,node3);
        ListNode node1 = new ListNode(1,node2);
        ListNode ans = reverseList(node1);
        System.out.println("ans:" + ans);
    }
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        //如果当前节点不为空，则循环
        while (curr != null) {
            //先获取下一个节点
            ListNode next = curr.next;
            //当前取出的节点的下一个元素指向头节点
            curr.next = prev;
            //头节点指向当前元素
            prev = curr;
            //指针往后移动
            curr = next;
        }
        //返回头节点
        return prev;
    }
}

