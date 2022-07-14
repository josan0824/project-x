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
 在遍历链表时，将当前节点的 \textit{next}next 指针改为指向前一个节点。由于节点没有引用其前一个节点，因此必须事先存储其前一个节点。
 在更改引用之前，还需要存储后一个节点。最后返回新的头引用。
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
        //存储前面的节点
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            //反向指向前一个元素
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}

