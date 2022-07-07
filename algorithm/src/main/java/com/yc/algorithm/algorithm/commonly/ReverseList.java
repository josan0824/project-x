package com.yc.algorithm.algorithm.commonly;

/**
 * @author: josan_tang
 * @create_date: 2022/7/6 11:28
 * @desc: 连表反正
 * @version:
 */
public class ReverseList {

    static class ListNode<V> {
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
     * 递归实现反转
     *
     * @param head
     * @return
     */
    private static ListNode reverseList(ListNode head) {
 /*       if (head == null || head.next == null) {
            //到达尾节点
            return head;
        }
        ListNode next = head.next;
        ListNode newHead = reverseList(next);
        next.next = head;
        head.next = null;
        return newHead;*/

        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode newHead = reverseList(next);
        next.next = head;
        //为了断开自己的下一个节点的联系，否之前的头结点还是指向了第二个节点
        head.next = null;
        return newHead;
    }

    /**
     * 链表反转-头插法
     *
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
        ListNode newHead = reverseList(n1);
        System.out.println(newHead.val);

    }


}
