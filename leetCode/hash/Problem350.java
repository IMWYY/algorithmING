package leetCode.hash;

import java.util.*;

/**
 * Given two arrays, write a function to compute their intersection.
 */
public class Problem350 {

    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (int j: nums2) {
            if (map.getOrDefault(j, 0) > 0) {
                list.add(j);
                map.put(j, map.get(j) - 1);
            }
        }
        int[] res = new int[list.size()];
        for (int i =0; i<list.size(); ++i) {
            res[i] = list.get(i);
        }
        return res;
    }
}
