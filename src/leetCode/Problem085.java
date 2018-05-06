package leetCode;

import java.util.Arrays;

/**
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest rectangle containing only 1's and return its area.
 * <p>
 * Example:
 * Input:
 * [
 * ["1","0","1","0","0"],
 * ["1","0","1","1","1"],
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]
 * ]
 * Output: 6
 * <p>
 * create by stephen on 2018/5/6
 */
public class Problem085 {

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        dp[0][0] = matrix[0][0] == '1' ? 1 : 0;
        for (int j = 1; j < matrix[0].length; ++j) {
            if (matrix[0][j] == '0') {
                dp[0][j] = dp[0][j-1];
            } else {

            }
        }


        int res = 0, width = 1, height = 1;

        return res;
    }


}
