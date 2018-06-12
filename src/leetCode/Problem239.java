package leetCode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very
 * left of the array to the very right. You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position. Return the max sliding window.
 * <p>
 * Example:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * <p>
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
 * <p>
 * Follow up:
 * Could you solve it in linear time?
 * create by stephen on 2018/6/5
 */
public class Problem239 {

    /**
     * 使用双向队列 具体看注释
     * O(n) time + O(n) time
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0 || nums.length == 0) return new int[0];
        Deque<Integer> queue = new ArrayDeque<>();
        int[] result = new int[(nums.length - k) <= 0 ? 1 : nums.length - k + 1];
        for (int i = 0; i < nums.length; ++i) {
            while (!queue.isEmpty() && queue.peek() < i - k + 1) {      // 将在窗口之外的元素移除
                queue.poll();
            }
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {  // 将小于新加元素的值移除 保证队列里的元素值从大到小排列
                queue.pollLast();
            }
            queue.offer(i);
            if (i >= k - 1) {
                result[i - k + 1] = nums[queue.peek()];
            }
        }
        return result;
    }

    /**
     * 用一个堆维持滑动窗口中的最大值
     * O(nlogn) time + O(n) time
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (k == 0 || nums.length == 0) return new int[0];
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < (nums.length <= k ? nums.length : k); ++i) {
            heap.add(nums[i]);
        }
        int[] result = new int[(nums.length - k) <= 0 ? 1 : nums.length - k + 1];
        result[0] = heap.peek();

        int start = 1;
        while (start < nums.length - k + 1) {
            heap.remove(nums[start - 1]);
            heap.add(nums[start + k - 1]);
            result[start++] = heap.peek();
        }
        return result;
    }
}
