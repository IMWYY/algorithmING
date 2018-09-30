package leetCode.binarySearch;

import java.util.PriorityQueue;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order,
 * find the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * <p>
 * Example:
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 * <p>
 * return 13.
 * create by stephen on 2018/9/30
 */
public class Problem378 {

    /**
     * 利用值的二分查找
     */
    public int kthSmallest(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0, j = matrix[0].length - 1;
            for (int[] aMatrix : matrix) {
                while (j >= 0 && aMatrix[j] > mid) j--;
                count += (j + 1);
            }
            if (count < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    /**
     * 利用最小堆 并执行k-1次poll 第k次poll就是结果
     * 每次poll出来 用比他大的下一个元素替换进去 保证每次poll出来的数字就是最小的
     */
    public int kthSmallest1(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
        for (int j = 0; j <= n - 1; j++) pq.offer(new Tuple(0, j, matrix[0][j]));
        for (int i = 0; i < k - 1; i++) {
            Tuple t = pq.poll();
            if (t.x == n - 1) continue;
            pq.offer(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y]));
        }
        return pq.poll().val;
    }

    private class Tuple implements Comparable<Tuple> {
        int x, y, val;

        public Tuple(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Tuple that) {
            return this.val - that.val;
        }
    }

    /**
     * 用最大堆并保持堆大小为k
     */
    public int kthSmallest2(int[][] matrix, int k) {
        if (matrix.length == 0 || matrix[0].length == 0 || k == 0) return 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int[] aMatrix : matrix) {
            if (maxHeap.size() == k && aMatrix[0] > maxHeap.peek()) {
                return maxHeap.peek();
            }
            for (int anAMatrix : aMatrix) {
                if (maxHeap.size() < k) {
                    maxHeap.add(anAMatrix);
                } else if (maxHeap.size() == k && maxHeap.peek() > anAMatrix) {
                    maxHeap.poll();
                    maxHeap.add(anAMatrix);
                }
            }
        }
        return maxHeap.peek();
    }
}
