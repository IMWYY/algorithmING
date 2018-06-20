package leetCode;

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
 * 使用双向链表+hash表实现的LRU缓存策略
 * 实际上java内置的LinkedHashMap即可以实现
 * <p>
 * create by stephen on 2018/4/26
 */
public class Problem146 {

    private Map<Integer, Node> map = new HashMap<>();
    private Node head, tail;
    private int capacity;

    public Problem146(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 获取节点值并调整节点的位置
     */
    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            node.before.after = node.after;
            node.after.before = node.before;
            node.after = head;
            node.before = null;
            head = node;
            map.put(key, node);
            return head.val;
        }

        return -1;
    }

    /**
     * 加入节点值 如果不存在就添加在链表尾 否则就更新值到链表头
     */
    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.val = value;
            node.before.after = node.after;
            node.after.before = node.before;
            node.after = head;
            node.before = null;
            head = node;
        } else {
            if (map.size() >= capacity) {
                map.remove(tail.val, tail);
                tail = tail.before;
                tail.after = null;
                //删除尾巴元素
            }
            node = new Node(value);
            tail.after = node;
            node.before = tail;
            node.after = null;
            tail = node;
        }
    }

    public class Node {
        public Node after;
        public Node before;
        public int val;

        public Node(int val) {
            this.val = val;
        }
    }
}
