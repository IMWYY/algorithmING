package leetCode.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 * <p>
 * Example 1:
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence:
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * <p>
 * create by stephen on 2018/10/1
 */
public class Problem373 {

    /**
     * 解法类似Problem378的最小堆解法
     * 用最小堆装入每一对pair 然后poll k次，每次poll的时候将下一次可能的候选pair放入最小堆
     */
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList<>();
        if (k == 0 || nums1.length == 0 || nums2.length == 0) {
            return res;
        }
        PriorityQueue<Pair> minHeap = new PriorityQueue<>();
        for (int i = 0; i < nums1.length; ++i) minHeap.add(new Pair(i, 0, nums1[i], nums2[0]));
        for (int i = 0; i < k; ++i) {
            if (minHeap.size() == 0) return res;
            Pair p = minHeap.poll();
            res.add(p.getData());
            if (p.v == nums2.length - 1) continue;
            minHeap.add(new Pair(p.u, p.v + 1, nums1[p.u], nums2[p.v + 1]));
        }
//        minHeap.add(new Pair(0, 0, nums1[0], nums2[0]));
//        boolean[][] flags = new boolean[nums1.length][nums2.length];
//        flags[0][0] = true;
//        for (int i = 0; i < k; ++i) {
//            if (minHeap.size() == 0) return res;
//            Pair p = minHeap.poll();
//            res.add(p.getData());
//            if (p.v < nums2.length - 1 && !flags[p.u][p.v + 1]) {
//                minHeap.add(new Pair(p.u, p.v + 1, nums1[p.u], nums2[p.v + 1]));
//                flags[p.u][p.v + 1] = true;
//            }
//            if (p.u < nums1.length - 1 && !flags[p.u + 1][p.v]) {
//                minHeap.add(new Pair(p.u + 1, p.v, nums1[p.u + 1], nums2[p.v]));
//                flags[p.u + 1][p.v] = true;
//            }
//        }

        return res;
    }

    private class Pair implements Comparable<Pair> {
        int u;
        int v;
        int valueU;
        int valueV;

        Pair(int u, int v, int valueU, int valueV) {
            this.u = u;
            this.v = v;
            this.valueU = valueU;
            this.valueV = valueV;
        }

        int[] getData() {
            return new int[]{valueU, valueV};
        }

        @Override
        public int compareTo(Pair o) {
            return this.valueU + this.valueV - o.valueU - o.valueV;
        }
    }

}

