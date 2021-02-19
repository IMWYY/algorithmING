package leetCode.heap;

import java.util.PriorityQueue;

/**
 * keep a max heap for the first half and a min heap for the second half.
 * Q: If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * A: use an array of size 100.
 * Q: If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * A: keep two seperated counter for # of elements larger than 100 and # of elements smaller than 0.
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