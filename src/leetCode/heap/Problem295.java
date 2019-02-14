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
     * Q: If all integer numbers from the stream are between 0 and 100, how would you optimize it?
     * A: 利用100个bucket，记录每个数字出现的次数
     * Q: If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
     * A: reservoir sampling?
     * https://stackoverflow.com/questions/10657503/find-running-median-from-a-stream-of-integers/10693752#10693752
     */
    private class MedianFinder {

        private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

        public void addNum(int num) {
            if (maxHeap.size() == 0) {
                maxHeap.add(num);
                return;
            }
            if (num > maxHeap.peek()) {
                minHeap.add(num);
            } else {
                maxHeap.add(num);
            }
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size() + 1) {
                maxHeap.add(minHeap.poll());
            }
        }

        public double findMedian() {
            return maxHeap.size() == minHeap.size() ? (minHeap.peek() + maxHeap.peek()) * 0.5 :
                    maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
        }
    }
}
