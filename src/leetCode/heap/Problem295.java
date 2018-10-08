package leetCode.heap;

import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even,
 * there is no middle value. So the median is the mean of the two middle value.
 * <p>
 * For example,
 * [2,3,4], the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * <p>
 * Design a data structure that supports the following two operations:
 * <p>
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * <p>
 * create by stephen on 2018/10/8
 */
public class Problem295 {

    /**
     * 利用两个堆 一个最大堆和一个最小堆
     * 每次添加元素的时候调整 通过两个堆的堆顶可以确定median
     * todo: 也可以用AVL树
     * todo: Follow up:
     * todo: If all integer numbers from the stream are between 0 and 100, how would you optimize it?
     * todo: If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
     * 如果在99%在0-100 可以记录个数？
     */
    private class MedianFinder {

        private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

        public void addNum(int num) {
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
            if (maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
        }

        public double findMedian() {
            return maxHeap.size() == minHeap.size() ? (minHeap.peek() + maxHeap.peek()) * 0.5 : maxHeap.peek();
        }
    }
}
