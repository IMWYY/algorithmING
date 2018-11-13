package leetCode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 * it should invalidate the least recently used item before inserting a new item.
 * <p>
 * create by stephen on 2018/4/26
 */
public class Problem146 {
    private class Node {
        Node after;
        Node before;
        int val;
        int key;

        Node(int key, int val) {
            this.val = val;
            this.key = key;
        }
    }

    private Map<Integer, Node> map = new HashMap<>();
    private Node head, tail;  // 伪节点
    private int capacity;

    /**
     * 使用双向链表+hash表实现的LRU缓存策略
     * 其中双向链表包含一个头尾伪节点
     * <p>
     * 实际上java内置的LinkedHashMap即可以实现
     */
    public Problem146(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.after = tail;
        tail.before = head;
    }

    /**
     * 获取节点值并调整节点的位置
     */
    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            moveToHead(node);
            return node.val;
        }
        return -1;
    }

    /**
     * 加入节点值 如果不存在就添加在链表头
     * 否则就删除尾巴元素 再添加到链表头
     */
    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.val = value;
            moveToHead(node);
        } else {
            if (map.size() >= capacity) {
                map.remove(tail.before.key);
                tail.before = tail.before.before;   //删除尾巴元素
                tail.before.after = tail;
            }
            node = new Node(key, value);
            moveToHead(node);
            map.put(key, node);
        }
    }


    private void moveToHead(Node node) {
        if (node.before == head) return;
        if (node.before != null) node.before.after = node.after;
        if (node.after != null) node.after.before = node.before;
        node.after = head.after;
        node.before = head;
        head.after.before = node;
        head.after = node;
    }
}
