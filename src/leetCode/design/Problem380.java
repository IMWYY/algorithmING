package leetCode.design;

import java.util.*;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 * <p>
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements.
 * Each element must have the same probability of being returned.
 * create by stephen on 2018/7/3
 */
public class Problem380 {

    private List<Integer> keyList;
    private Map<Integer, Integer> index2dataMap;
    private Map<Integer, Integer> data2indexMap;
    private int idGenerator;
    private Random random;

    /**
     * Initialize your data structure here.
     */
    public Problem380() {
        this.random = new Random();
        this.keyList = new ArrayList<>();
        this.index2dataMap = new HashMap<>();
        this.data2indexMap = new HashMap<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (data2indexMap.containsKey(val)) {
            return false;
        }
        int id = idGenerator++;
        data2indexMap.put(val, id);
        index2dataMap.put(id, val);
        keyList.add(id);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        int id = data2indexMap.getOrDefault(val, -1);
        if (id == -1) return false;
        data2indexMap.remove(val);
        index2dataMap.remove(id);
        keyList.remove((Integer) id);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int id = keyList.get(random.nextInt(keyList.size()));
        return index2dataMap.get(id);
    }
}
