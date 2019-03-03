package leetCode.array;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 * <p>
 * Example:
 * <p>
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * create by stephen on 2018/6/28
 */
public class Problem053 {

	/**
	 * 贪心算法 如果当前计算的第i个位置 tempSum < nums[i+1]
	 * 那么说明起点需要更换一下 tempsum重置为0
	 * O(n) time + O(1) space
	 */
	public int maxSubArray(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		int curMax = nums[0];
		int allMax = nums[0];
		for (int i = 1; i < nums.length; i++) {
			curMax = Math.max(nums[i], curMax + nums[i]);
			allMax = Math.max(allMax, curMax);
		}
		return allMax;
	}

	/**
	 * 优化的DP算法 思路同上
	 * O(n) time + O(n) space
	 */
	public int maxSubArray1(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
		dp[0] = nums[0];
		int max = dp[0];

		for (int i = 1; i < n; i++) {
			dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
			max = Math.max(max, dp[i]);
		}

		return max;
	}
}
