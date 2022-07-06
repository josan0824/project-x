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

    /**
     * 递归实现反转
     * @param head
     * @return
     */
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

    /**
     * 链表反转-头插法
     * @param head
     * @return
     */
    public static ListNode reverseListByInsertHead(ListNode head) {
        ListNode newHead = new ListNode(-1);
        while (head != null) {
            ListNode next = head.next;
            //插入到newHead的头部
            head.next = newHead.next;
            newHead.next = head;
            //后移
            head = next;
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode<>(1);
        ListNode n2 = new ListNode<>(2);
        n1.next = n2;
        ListNode n3 = new ListNode<>(3);
        n2.next = n3;
        ListNode newHead = reverseListByInsertHead(n1);
        System.out.println(newHead.value);

    }



}
