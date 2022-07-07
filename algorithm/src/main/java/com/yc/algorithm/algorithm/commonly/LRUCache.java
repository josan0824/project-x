package com.yc.algorithm.algorithm.commonly;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: josan_tang
 * @create_date: 2022/7/6 9:25
 * @desc:LRU是指最近最少使用，是一种缓存淘汰算法。
 * @version:
 */
class LRUCache {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        System.out.println(lruCache.get(1));
        lruCache.put(3,3);
        System.out.println(lruCache.get(2));
    }

    class Entry {
        Entry prev;
        Entry next;
        int key;
        int value;

        public Entry() {
        }

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 缓存容量
     */
    private int capacity;

    /**
     * 缓存占有数
     */
    private int size;

    /**
     * 存放数据的map
     */
    Map<Integer, Entry> cache;

    /**
     * 头部、尾部虚拟节点
     */
    private Entry head, tail;

    /**
     * 初始化
     *
     * @param capacity
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        cache = new HashMap<>(capacity);
        head = new Entry();
        tail = new Entry();
        head.next = tail;
        tail.prev = head;
    }

    public void put(int key, int value) {
        Entry entry = cache.get(key);
        if (entry != null) {
            //如果存在，更新数据，移动到尾部
            entry.value = value;
            handleMoveToTail(entry);
        } else {
            //如果不存在
            //如果现在容器已满，需要先删除头部节点
            if (size == capacity) {
                Entry firstEntry = head.next;
                deleteEntry(firstEntry);
                //这里移除的key是有效首节点的key，不是设置的key,也不能用head.nexe.key，因为上述会改变head.nexe
                cache.remove(firstEntry.key);
                size--;
            }
            //把节点加入到连表尾部
            Entry newEntry = new Entry(key, value);
            addToTail(newEntry);
            cache.put(key, newEntry);
            size++;
        }
    }

    private void handleMoveToTail(Entry entry) {
        deleteEntry(entry);
        addToTail(entry);
    }

    /**
     * 删除节点
     *
     * @param entry
     */
    private void deleteEntry(Entry entry) {
        entry.prev.next = entry.next;
        entry.next.prev = entry.prev;
    }

    /**
     * 增加到尾部
     *
     * @param entry
     */
    private void addToTail(Entry entry) {
        entry.prev = tail.prev;
        entry.next = tail;
        tail.prev.next = entry;
        tail.prev = entry;
    }

    public int get(int key) {
        Entry entry = cache.get(key);
        if (entry != null) {
            handleMoveToTail(entry);
            return entry.value;
        } else {
            return -1;
        }
    }
}

