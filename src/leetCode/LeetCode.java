package leetCode;

import java.util.Arrays;

/**
 * create by stephen on 2018/4/9
 */
public class LeetCode {

    public static void main(String[] args) {
    }


    /**
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Determine if you are able to reach the last index.
     */
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    /**
     * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
     * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
     */
    public int numSquares(int n) {
        int temp = (int) Math.sqrt(n);
        if (temp * temp == n) {
            return 1;
        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);
        for (int i = 1; i <= n; ++i) {
            temp = (int) Math.sqrt(i);
            if (temp * temp != i) {
                int result = Integer.MAX_VALUE;
                for (int j = 1; j <= temp; ++j) {
                    result = Math.min(result, dp[i - j * j] + 1);
                }
                dp[i] = result;
            }
        }

        return dp[n];
    }
}
