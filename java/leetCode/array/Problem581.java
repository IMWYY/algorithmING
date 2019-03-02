package leetCode.array;

import java.util.Arrays;

/**
 * Given an integer array, you need to find one continuous subarray that if you only
 * sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
 * <p>
 * You need to find the shortest such subarray and output its length.
 * <p>
 * Example 1:
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * <p>
 * Note:
 * Then length of the input array is in range [1, 10,000].
 * The input array may contain duplicates, so ascending order here means <=.
 * <p>
 * create by stephen on 2018/5/8
 */
public class Problem581 {

	/**
	 * one-pass
	 * 找到比最小值小的最前面的位置start 和 比最大值大的最后面的位置end
	 * O(n) time + O(1) space
	 */
	public int findUnsortedSubarray(int[] nums) {
		int i = 0, j = -1;
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

		for (int l = 0, r = nums.length - 1; r >= 0; l++, r--) {
			max = Math.max(max, nums[l]);
			if (nums[l] < max)  // 找到比max小的最后面一个
				j = l;

			min = Math.min(min, nums[r]);
			if (nums[r] > min)     // 找到比min大的最前面一个
				i = r;
		}

		return (j - i + 1);
	}

	/**
	 * two-pass
	 * 记录从0-i位置的最大值 和 i到n-1位置的最小值
	 * 找到第一个大于最小值的位置和第一个小于最大值的位置
	 * O(n) time + O(n) space
	 */
	public int findUnsortedSubarray1(int[] nums) {
		int l = 0, r = nums.length - 1;

		while (l < r && nums[l] <= nums[l + 1])
			l++;

		if (l >= r)
			return 0;

		while (nums[r] >= nums[r - 1])
			r--;

		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

		for (int k = l; k <= r; k++) {
			max = Math.max(max, nums[k]);
			min = Math.min(min, nums[k]);
		}

		while (l >= 0 && min < nums[l])
			l--;
		while (r < nums.length && nums[r] < max)
			r++;

		return (r - l - 1);
	}

	/**
	 * 利用排序 然后比较当前数组和排序后的数组的首尾位置数值偏差
	 * O(nlogn) time + O(1) space
	 */
	public int findUnsortedSubarray2(int[] nums) {
		int[] snums = nums.clone();
		Arrays.sort(snums);
		int start = snums.length, end = 0;
		for (int i = 0; i < snums.length; i++) {
			if (snums[i] != nums[i]) {
				start = Math.min(start, i);
				end = Math.max(end, i);
			}
		}
		return (end - start >= 0 ? end - start + 1 : 0);
	}
}

