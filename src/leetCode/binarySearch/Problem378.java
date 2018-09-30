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
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 5, 9}, {10, 11, 13}, {12, 13, 15}
        };
        System.out.println(new Problem378().kthSmallest(matrix, 8));
    }

    /**
     * 用最大堆并保持堆大小为k
     */
    public int kthSmallest(int[][] matrix, int k) {
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
