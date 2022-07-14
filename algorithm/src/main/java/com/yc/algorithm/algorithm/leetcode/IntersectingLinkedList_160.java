package com.yc.algorithm.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: josan_tang
 * @create_date: 2022/7/14 10:06
 * @desc:
 * @version:
 */
class Test {
    public static void main(String[] args) {
        IntersectingLinkedList_160 intersectingLinkedList_160 = new IntersectingLinkedList_160();

        ListNode1 listNode2 = new ListNode1(2);
        ListNode1 listNodeA = new ListNode1(1);
        listNodeA.next = listNode2;
        ListNode1 listNodeB = new ListNode1(1);
        listNodeB.next = listNode2;

        ListNode1 listNode = intersectingLinkedList_160.getIntersectionNode2(listNodeA, listNodeB);
        System.out.println("结束");
    }
}

 class ListNode1 {
     int val;
     ListNode1 next;
     ListNode1(int x) {
         val = x;
         next = null;
     }
 }
public class IntersectingLinkedList_160 {
    public ListNode1 getIntersectionNode(ListNode1 headA, ListNode1 headB) {
        Set<ListNode1> existSet = new HashSet();
        //把A的加入到集合中
        while (headA != null) {
            existSet.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            //遍历B,如果A中的包含B，则证明相交，则返回
            if (existSet.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }


    public ListNode1 getIntersectionNode2(ListNode1 headA, ListNode1 headB) {
        //如果两个头结点相同，直接返回
        if (headA == headB) {
            return headA;
        }
        ListNode1 pA = headA;
        ListNode1 pB = headB;
        while(pA != pB) {
            //如果找到尾节点还没找到，则指向另一个连表头结点
            //假设A、B前面不相同的元素个数分别为a、b,相同元素个数为c,经过一次遍历之后
            //如果a=b,则不需要到pA、pB到尾部，就能找到交叉数据
            //如果a != b，则需要遍历a+b+c次，两个会走到一样的位置
            pA  = (pA == null) ? (headB) : pA.next;
            pB = (pB == null) ? (headA) : pB.next;
            if (pA == pB) {
                return pA;
            }
        }
        return null;
    }
}
