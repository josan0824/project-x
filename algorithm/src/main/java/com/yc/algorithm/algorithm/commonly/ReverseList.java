package com.yc.algorithm.algorithm.commonly;

/**
 * @author: josan_tang
 * @create_date: 2022/7/6 11:28
 * @desc: 连表反正
 * @version:
 */
public class ReverseList {

    static class ListNode<V> {
        ListNode next;
        V value;

        public ListNode(V v) {
            this.value = v;
        }
    }

    private static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            //到达尾节点
            return head;
        }
        ListNode next = head.next;
        ListNode newHead =  reverseList(next);
        next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode<>(1);
        ListNode n2 = new ListNode<>(2);
        n1.next = n2;
        ListNode n3 = new ListNode<>(3);
        n2.next = n3;
        ListNode newHead = reverseList(n1);
        System.out.println(newHead.value);

    }
}
