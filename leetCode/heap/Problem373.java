package leetCode.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 */
public class Problem373 {

    // push (0, 1), (0, 2)... (0, nums2.size() - 1) into the minheap
    // then add (i+1, j) when pop (i, j)
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList<>();
        if (k == 0 || nums1.length == 0 || nums2.length == 0) return res;
        PriorityQueue<Pair> minHeap = new PriorityQueue<>();
        for (int i = 0; i < nums1.length; ++i) minHeap.add(new Pair(i, 0, nums1[i], nums2[0]));
        for (int i = 0; i < k; ++i) {
            if (minHeap.size() == 0) return res;
            Pair p = minHeap.poll();
            res.add(p.getData());
            if (p.v == nums2.length - 1) continue;
            minHeap.add(new Pair(p.u, p.v + 1, nums1[p.u], nums2[p.v + 1]));
        }
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

