package leetCode.array;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [
 * [0,1,2,0],
 * [3,4,5,2],
 * [1,3,1,5]
 * ]
 * Output:
 * [
 * [0,0,0,0],
 * [0,4,5,0],
 * [0,3,1,0]
 * ]
 * create by stephen on 2018/7/4
 */
public class Problem073 {

    /**
     * 利用第一行和第一列来记录改行/列是否出现过1
     * 因此第一行和第一列要单独分出来
     */
    public void setZeroes(int[][] matrix) {
        boolean firstRow = false, firstCol = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) firstRow = true;
                    if (j == 0) firstCol = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstRow) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if (firstCol) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    /**
     * 左移一位 用最右边一位数字表示初始状态下数字是否为0
     * 0 表示 该数字为0
     * 1 表示该数字不为0
     * 但是无法解决整数溢出的问题 左移动相当于数字乘以2 导致整数溢出
     */
    public void setZeroes1(int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (matrix[i][j] == 0) {
                    matrix[i][j] = matrix[i][j] << 1;
                } else {
                    matrix[i][j] = matrix[i][j] << 1;
                    matrix[i][j]++;
                }
            }
        }

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if ((matrix[i][j] & 1) == 0) {
                    for (int k = 0; k < matrix[0].length; ++k) matrix[i][k] &= 1;
                    for (int k = 0; k < matrix.length; ++k) matrix[k][j] &= 1;
                }
            }
        }

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                matrix[i][j] = matrix[i][j] >> 1;
            }
        }
    }
}
