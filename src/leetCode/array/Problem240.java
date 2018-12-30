package leetCode.array;

import java.util.Arrays;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * <p>
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * Consider the following matrix:
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * <p>
 * Example 1:
 * Input: matrix, target = 5
 * Output: true
 * <p>
 * Example 2:
 * Input: matrix, target = 20
 * Output: false
 * <p>
 * create by stephen on 2018/5/18
 */
public class Problem240 {

    /**
     * 从右上角(或者左下角)开始查找
     * O(m+n) time + O(1) space
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int x = 0, y = matrix[0].length - 1;

        while (x <= matrix.length && y >= 0) {
            if (matrix[x][y] == target) return true;
            if (matrix[x][y] > target) y--;
            else if (matrix[x][y] < target) x++;
        }
        return false;
    }

    /**
     * 利用二分查找 从最后一行往前
     * O(mlogn) time + O(1) space
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int x = matrix.length - 1, y = 0;

        while (x >= 0) {
            if (matrix[x][y] > target) {
                x--;
            } else if (matrix[x][y] == target) {
                return true;
            } else {
                int pos = Arrays.binarySearch(matrix[x], target);
                if (pos >= 0) return true;
                x--;
            }
        }

        return false;
    }
}
