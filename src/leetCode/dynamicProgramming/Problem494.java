package leetCode.dynamicProgramming;


import java.util.Arrays;

/**
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
 * Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * <p>
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * Note:
 * The length of the given array is positive and will not exceed 20.
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 * <p>
 * create by stephen on 2018/5/27
 */
public class Problem494 {


    /**
     * DFS 解法+ memorization
     * 这里加1000 使sum结果都映射到数组里
     * 也可以用map
     */
    public int findTargetSumWays(int[] nums, int S) {
        int[][] memo = new int[nums.length][2001];
        for (int[] aMemo : memo) {
            Arrays.fill(aMemo, Integer.MAX_VALUE);
        }
        return dfs(nums, 0, 0, S, memo);
    }

    private int dfs(int[] nums, int i, int sum, int s, int[][] memo) {
        if (i == nums.length) {
            if (sum == s) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (memo[i][sum + 1000] != Integer.MAX_VALUE) return memo[i][sum + 1000];

            int a = dfs(nums, i + 1, sum + nums[i], s, memo);
            int b = dfs(nums, i + 1, sum - nums[i], s, memo);
            memo[i][sum + 1000] = a + b;
            return memo[i][sum + 1000];
        }
    }

    /**
     * 动态规划解法: 将问题转换为子集和的问题
     * P表示正数集合 N表示负数集合
     * sum(P) - sum(N) = target
     * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
     * 2 * sum(P) = target + sum(nums)
     * sum(P) = target + sum(nums)/2
     * <p>
     * 记数组的和sum(nums) = S, 则得到
     * 正数集合的和 sum(P) =  target + S/2 典型背包问题
     */
    public int findTargetSumWays1(int[] nums, int S) {
        int sum = 0;
        for (int n : nums) sum += n;
        return (sum < S || S < -sum || (sum + S) % 2 == 1) ? 0 : subTarget(nums, (sum + S) >> 1);
    }

    private int subTarget(int[] nums, int target) {
        int[][] dp = new int[nums.length + 1][target + 1];
        for (int[] aDp : dp) {
            Arrays.fill(aDp, 0);
        }
        dp[0][0] = 1;

        for (int i = 1; i < nums.length + 1; ++i) {
            for (int j = 0; j < target + 1; ++j) {
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]]; // 选num[i-1]为正数
                }
                dp[i][j] += dp[i - 1][j];  // 不选num[i-1]为正数
            }
        }

        return dp[nums.length][target];
    }
}
