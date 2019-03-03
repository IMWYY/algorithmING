package leetCode.array;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 * <p>
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * create by stephen on 2018/10/9
 */
public class Problem215 {

    /**
     * 利用最小堆 并保存堆大小为k
     * O(nlogk) time + O(k) space
     */
    public int findKthLargest(int[] nums, int k) {
        if (k == 0 || nums.length == 0) return 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i : nums) {
            if (minHeap.size() < k) {
                minHeap.add(i);
            } else {
                if (minHeap.peek() < i) {
                    minHeap.poll();
                    minHeap.add(i);
                }
            }
        }
        return minHeap.peek();
    }

    /**
     * 利用选择排序的思想
     * randomize the input 来保证时间复杂度为O(n)
     * O(n) time + O(1) space
     */
    public int findKthLargest1(int[] nums, int k) {
        shuffle(nums);
        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    // randomize the input
    private void shuffle(int a[]) {
        final Random random = new Random();
        for (int ind = 1; ind < a.length; ind++) {
            final int r = random.nextInt(ind + 1);
            exch(a, ind, r);
        }
    }

    /**
     * 每次执行完partition 按照a[lo] 将数组分成两部分
     * j位置前的数小于num[j] j位置后的数大于num[j]
     */
    private int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (i < hi && a[++i] < a[lo]) ;
            while (j > lo && a[lo] < a[--j]) ;
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private void exch(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
