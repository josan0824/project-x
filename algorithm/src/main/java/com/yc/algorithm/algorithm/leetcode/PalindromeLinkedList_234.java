package com.yc.algorithm.algorithm.leetcode;

/**
 * @author: josan_tang
 * @create_date: 2022/7/14 11:36
 * @desc:给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * @version:
 */
class Test3 {
    public static void main(String[] args) {
        ListNode3 node4 = new ListNode3(1);
        ListNode3 node3 = new ListNode3(2, node4);
        ListNode3 node2 = new ListNode3(2,node3);
        ListNode3 node1 = new ListNode3(1,node2);
        PalindromeLinkedList_234 palindromeLinkedList_234 = new PalindromeLinkedList_234();
        System.out.println(palindromeLinkedList_234.isPalindrome(node1));
    }
}
class ListNode3 { int val;
    ListNode3 next;
    ListNode3() {}
    ListNode3(int val) { this.val = val; }
    ListNode3(int val, ListNode3 next) { this.val = val; this.next = next; }
  }
public class PalindromeLinkedList_234 {

    /**
     * 快慢指针
     * 1. 找到前半部分链表的尾节点。
     * 2. 反转后半部分链表。
     * 3. 判断是否回文。
     * 4. 恢复链表。
     * 5. 返回结果。
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode3 head) {
        if (head == null || head.next == null) {
            return true;
        }
        //找到前半部分链表的尾结点
        ListNode3 halfNode = endOfFirstHalf(head);
        //反转后半部分链表
        ListNode3 secondNodeStart = reverseList(halfNode.next);

        //判断回文
        while (secondNodeStart != null) {
            if (!(head.val == secondNodeStart.val)) {
                return false;
            }
            secondNodeStart = secondNodeStart.next;
            head = head.next;
        }
        return true;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    private ListNode3 reverseList(ListNode3 head) {
        ListNode3 prev = null;
        ListNode3 curr = head;
        while(curr != null) {
            ListNode3 next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    
    /**
     * 找到中间节点
     * 奇书和偶数怎么解决？
     * @param head
     * @return
     */
    private ListNode3 endOfFirstHalf(ListNode3 head) {
        ListNode3 fast = head;
        ListNode3 slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    
}
