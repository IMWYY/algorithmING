package leetCode.dynamicProgramming;

import java.util.Arrays;

/**
 * Given a positive integer n, find the least number of perfect square numbers
 * (for example, 1, 4, 9, 16, ...) which sum to n.
 * <p>
 * Example 1:
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * create by stephen on 2018/10/26
 */
public class Problem279 {

    /**
     * 动态规划 注意这里判断一个数是不是平方数的细节：
     * Math.pow((int)Math.sqrt(i), 2) == i 一定要将Math.sqrt(i)强制转换为int
     */
    public int numSquares(int n) {
        if (n < 4) return n;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n + 1; ++i) {
            if (Math.pow((int) Math.sqrt(i), 2) != i) {
                int cur = i;
                for (int j = 1; j * j < i; ++j) {
                    cur = Math.min(cur, dp[i - j * j] + 1);
                }
                dp[i] = cur;
            }
        }
        return dp[n];
    }

    /**
     * 利用数学算法
     * Based on Lagrange's Four Square theorem, there are only 4 possible results: 1, 2, 3, 4.
     * https://en.wikipedia.org/wiki/Lagrange%27s_four-square_theorem
     * https://www.alpertron.com.ar/4SQUARES.HTM
     */
    public int numSquares1(int n) {
        // If n is a perfect square, return 1.
        if (Math.pow((int) Math.sqrt(n), 2) == n) {
            return 1;
        }
        // The result is 4 if and only if n can be written in the
        // form of 4^k*(8*m + 7). Please refer to
        // Legendre's three-square theorem.
        while ((n & 3) == 0) { // n%4 == 0
            n >>= 2;
        }
        if ((n & 7) == 7) { // n%8 == 7
            return 4;
        }

        // Check whether 2 is the result.
        int sqrt_n = (int) (Math.sqrt(n));
        for (int i = 1; i <= sqrt_n; i++) {
            if (Math.pow((int) Math.sqrt(n - i * i), 2) == (n - i * i)) {
                return 2;
            }
        }

        return 3;
    }
}
