package leetCode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * create by stephen on 2018/7/5
 */
public class Problem054 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) return res;
        if (matrix.length == 1) {
            for (int i=0; i<matrix[0].length; ++i) res.add(matrix[0][i]);
            return res;
        } else if (matrix[0].length == 1) {
            for (int[] aMatrix : matrix) res.add(aMatrix[0]);
            return res;
        }

        int maxRow = matrix.length, maxCol = matrix[0].length;
        int maxRound = Math.min(maxCol, maxRow) / 2;

        for (int k = 0; k < maxRound; ++k) {
            for (int col = k; col < maxCol - k; ++col) res.add(matrix[k][col]);
            for (int row = k + 1; row < maxRow - k - 1; ++row) res.add(matrix[row][maxCol - k - 1]);
            for (int col = maxCol - k - 1; col >= k; --col) res.add(matrix[maxRow - k - 1][col]);
            for (int row = maxRow - k - 2; row >= k + 1; --row) res.add(matrix[row][k]);
        }

        // 如果中间还存在一层没有遍历 添加上去
        if (maxCol == maxRow && maxRow % 2 == 1) {
            res.add(matrix[maxRound][maxRound]);
        } else if (maxCol > maxRow && maxRow % 2 == 1) {
            for (int col=maxRound; col<maxCol-maxRound; ++col) res.add(matrix[maxRound][col]);
        } else if (maxCol < maxRow && maxCol % 2 == 1) {
            for (int row=maxRound; row<maxRow-maxRound; ++row) res.add(matrix[row][maxRound]);
        }
        return res;
    }
}
