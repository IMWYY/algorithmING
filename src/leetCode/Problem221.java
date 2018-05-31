package leetCode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * <p>
 * Example:
 * <p>
 * Input:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * Output: 4
 * create by stephen on 2018/5/31
 */
public class Problem221 {


    /**
     * 动态规划算法 dp[i][j] 表示正方形右下角坐标为(i,j)
     * 这里构造动态规划的转换表发现仍可以继续优化，只用一维数组
     * O(mn) time + O(nm) space （ or O (n) space）
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        int result =0;
        for (int i=0; i<dp.length; ++i) {
            for (int j=0; j<dp[0].length; ++j) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = 1;
                    result = Math.max(result, 1);
                } else {
                    dp[i][j] = 0;
                }
                if (i > 0 && j>0 && matrix[i][j] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    result = Math.max(result, dp[i][j] * dp[i][j]);
                }
            }
        }
        return result;
    }


    /**
     * 借鉴leetcode 84 和 85的做法 （不太可行 largestRectangleArea方法无法计算正方形的最大面积）
     * O(mn) time + O(nm) space
     */
    public int maximalSquare1(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] height = new int[matrix[0].length];
        Arrays.fill(height, 0);
        int result = 0;
        for (char[] aMatrix : matrix) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (aMatrix[j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            result = Math.max(result, largestRectangleArea(height));
        }
        return result;
    }

    @SuppressWarnings("all")
    private int largestRectangleArea(int[] heights) {
        if (heights.length == 0) return 0;
        if (heights.length == 1) return heights[0];
        int res = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= heights.length; ) {
            int bar = i == heights.length ? 0 : heights[i];
            if (stack.isEmpty() || bar >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int h = heights[stack.pop()];
                while (!stack.isEmpty() && h == heights[stack.peek()]) {
                    stack.pop();
                }
                int left = stack.isEmpty() ? 0 : stack.peek() + 1; //计算的是peek后面一位作为左下标
                if (i - left == h) {                        // 计算正方形的面积
                    res = Math.max(res, h * (i - left));
                }
            }
        }
        return res;
    }
}
