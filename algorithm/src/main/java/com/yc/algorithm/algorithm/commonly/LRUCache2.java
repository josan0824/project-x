package com.yc.algorithm.algorithm.commonly;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: josan_tang
 * @create_date: 2022/7/6 9:25
 * @desc:LRU是指最近最少使用，是一种缓存淘汰算法。
 * @version:
 */
class LRUCache2<K, V> {

    public static void main(String[] args) {
        LRUCache2 lruCache2 = new LRUCache2(3);
        lruCache2.put(1,2);
        lruCache2.put(2,3);
        lruCache2.put(3,4);
        lruCache2.put(4,5);

    }

    /**
     * 节点数据
     */
    class DLinkedNode<K, V> {
        K key;
        V value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {

        }
        public DLinkedNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    //缓存容量
    private final int capacity;
    //缓存占用量
    private int size;
    //虚拟的头尾节点
    private DLinkedNode<K, V> head;
    private DLinkedNode<K, V> tail;
    //哈希表，记录双向列表节点的地址值
    private Map<K, DLinkedNode<K, V>> cache;

    /**
     * 构造方法
     * @param capacity
     */
    public LRUCache2(int capacity) {
        this.capacity = capacity;
        head = new DLinkedNode<>();
        tail = new DLinkedNode<>();
        head.next = tail;
        tail.prev = head;
        size = 0;
        cache = new HashMap<>(capacity);
    }

    public V get(K k) {
        DLinkedNode<K, V> dLinkedNode = cache.get(k);
        if (dLinkedNode != null) {
            moveToTail(dLinkedNode);
            return dLinkedNode.value;
        }
        return null;
    }

    /**
     * 存入值
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        //查询是否存在
        DLinkedNode<K, V> dLinkedNode = cache.get(key);
        if (dLinkedNode != null) {
            //缓存命中
            //更新value,并把数据放到尾节点
            dLinkedNode.value = value;
            moveToTail(dLinkedNode);
        } else {
            //缓存未命中
            if (size == capacity) {
                //已经满了，则把头部节点删除
                DLinkedNode<K, V> h = head.next;
                deleteNode(h);
                cache.remove(h.key);
                size --;
            }
            //把新节点加入到尾部
            DLinkedNode<K, V> newDLinkedNode = new DLinkedNode<>(key, value);
            addToTail(newDLinkedNode);
            cache.put(key, newDLinkedNode);
            size++;
        }
    }

    /**
     * 移动到结尾
     */
    private void moveToTail(DLinkedNode dLinkedNode) {
        //删除节点
        deleteNode(dLinkedNode);
        //把节点增加到尾部
        addToTail(dLinkedNode);
    }

    /**
     * 删除节点
     * @param dLinkedNode
     */
    private void deleteNode(DLinkedNode dLinkedNode) {
        dLinkedNode.prev.next = dLinkedNode.next;
        dLinkedNode.next.prev = dLinkedNode.prev;
    }

    /**
     * 节点加入到队尾
     * @param dLinkedNode
     */
    private void addToTail(DLinkedNode dLinkedNode) {
        tail.prev.next = dLinkedNode;
        tail.prev = dLinkedNode;
        dLinkedNode.prev = tail.prev;
        dLinkedNode.next = tail;
    }

}
