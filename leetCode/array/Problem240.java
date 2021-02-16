package leetCode.array;

import java.util.Arrays;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * Consider the following matrix:
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
