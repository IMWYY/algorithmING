package leetCode;

import java.util.Arrays;
import java.util.Stack;

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

    /**
     * 利用Problem084的解法 对于每一行如果每个位置j，计算竖直方向1的个数作为高度
     *  O(n^2) time + O(n) space
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;

        int res = 0;
        int[] height = new int[matrix[0].length];
        Arrays.fill(height, 0);

        for (char[] aMatrix : matrix) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (aMatrix[j] == '0') {
                    height[j] = 0;
                } else {
                    height[j]++;
                }
            }
            res = Math.max(res, largestRectangleArea(height));
        }

        return res;
    }

    @SuppressWarnings("all")
    private int largestRectangleArea(int[] height) {
        int len = height.length;
        Stack<Integer> s = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {
            int h = (i == len ? 0 : height[i]);
            if (s.isEmpty() || h >= height[s.peek()]) {
                s.push(i);
            } else {
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }

}
