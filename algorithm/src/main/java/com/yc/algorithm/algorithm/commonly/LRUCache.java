package com.yc.algorithm.algorithm.commonly;

import java.util.HashMap;

/**
 * @author: josan_tang
 * @create_date: 2022/7/6 9:25
 * @desc:LRU是指最近最少使用，是一种缓存淘汰算法。
 * @version:
 */
class LRUCache {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 1);
    }

    class DLinkedNode{
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {};//一定要有无参构造方法  否则head tail无法初始化
        public DLinkedNode(int key,int value){
            this.key = key;
            this.value = value;
        }
    }
    private HashMap<Integer,DLinkedNode> cache = new HashMap<>();// 存放缓存的信息 缓存的结构是键值对
    private DLinkedNode head;
    private DLinkedNode tail;
    private int capacity;
    private int size;

    public LRUCache(int capacity) {
        //初始化 建立 head和tail的关联
        this.capacity = capacity;
        size = 0;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if(node == null){
            return -1;
        }
        moveToHead(node);
        return node.value;
    }
    public void put(int key,int value) {
        DLinkedNode node = cache.get(key);
        if(node==null){
            DLinkedNode newhead = new DLinkedNode(key,value);
            cache.put(key,newhead);
            addToHead(newhead);
            size++;
            if(size>capacity){// 核心代码
                DLinkedNode res = removeTail();// 删除缓存时需要同步到cache中
                cache.remove(res.key);
                size--;
            }
        }else{
            node.value = value;
            moveToHead(node);
        }
    }

    public void addToHead(DLinkedNode node){// 添加到头部
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    private void moveToHead(DLinkedNode node) {// 移动到头部 = 删除该节点并添加该节点到头部
        // 删除前驱后继关系  和头结点及其next建立联系
        removeNode(node);
        addToHead(node);
    }
    private void removeNode(DLinkedNode node) {//移除某一节点
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    public DLinkedNode removeTail(){// 移除最近最少使用的缓存 也就是尾结点
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
}
