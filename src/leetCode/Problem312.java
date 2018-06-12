package leetCode;

/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons.
 * If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * Note:
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * <p>
 * Example:
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * create by stephen on 2018/5/23
 */
@SuppressWarnings("all")
public class Problem312 {

    /**
     * 利用memorization 不过这里是反着思考。
     * 先考虑最后被burst的气球，然后从后往前找气球，因为最后一个气球不会被干扰，
     * 假设最后一个位置为i，那么coins就是 numbers[0] * numbers[i] * numbers[n]
     * 然后以i分割，分别计算两边（left-i, i-right）的值
     * 需要注意的是：所有的0需要先去除
     */
    public int maxCoins(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] numbers = new int[nums.length + 2];
        int n = 1;
        for (int x : nums) {
            if (x > 0) numbers[n++] = x;            // key point: 去除所有0
        }
        numbers[0] = numbers[n++] = 1;

        int[][] memo = new int[n][n];
        return memorization(memo, numbers, 0, n - 1);
    }

    private int memorization(int[][] memo, int[] numbers, int left, int right) {
        if (left + 1 >= right) return 0;
        if (memo[left][right] > 0) return memo[left][right];
        int ans = 0;
        for (int i = left + 1; i < right; ++i) {
            ans = Math.max(ans, numbers[left] * numbers[i] * numbers[right]
                    + memorization(memo, numbers, left, i) + memorization(memo, numbers, i, right));
        }
        memo[left][right] = ans;
        return ans;
    }

    /**
     * 动态规划做法 思路和memorization一样 不过这里是将递归改为循环。
     * 需要注意的是，这里是将gap从2到n-1 就是burst的范围
     */
    @SuppressWarnings("all")
    public int maxCoins1(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] numbers = new int[nums.length + 2];
        int n = 1;
        for (int x : nums) {
            if (x > 0) numbers[n++] = x;            // key point: 去除所有0
        }
        numbers[0] = numbers[n++] = 1;

        int[][] dp = new int[n][n];

        for (int gap = 2; gap < n; ++gap) {              // key point: gap from 0 to n-1
            for (int left = 0; left < n - gap; ++left) {
                int right = left + gap;
                for (int i = left + 1; i < right; ++i) {
                    dp[left][right] = Math.max(dp[left][right], numbers[left] * numbers[i] * numbers[right]
                            + dp[left][i] + dp[i][right]);
                }
            }
        }

        return dp[0][n - 1];

    }
}
