package leetCode.array;

import java.util.Stack;

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
	 * 改进方案 只遍历一遍
	 * O(n) time + O(1) space
	 */
	public int findUnsortedSubarray1(int[] nums) {
		if (nums.length <= 1)
			return 0;

		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		int start = 0, end = 0;
		for (int i = 0; i < nums.length; ++i) {
			max = Math.max(max, nums[i]);
			min = Math.min(min, nums[nums.length - 1 - i]);
			if (nums[nums.length - i - 1] > min)
				start = nums.length - i - 1;        // start是比min大的最前面一个位置
			if (nums[i] < max)  // end点是比max小的最后一个位置
				end = i;
		}

		return end == start ? 0 : end - start + 1;
	}

	/**
	 * 记录从0-i位置的最大值和n-1到i位置的最小值
	 * 找到第一个大于最小值的位置和第一个小于最大值的位置
	 * O(n) time + O(n) space
	 */
	public int findUnsortedSubarray(int[] nums) {
		Stack<Integer> stack = new Stack<>();
		int l = nums.length, r = 0;
		for (int i = 0; i < nums.length; i++) {
			while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
				l = Math.min(l, stack.pop());
			stack.push(i);
		}
		stack.clear();
		for (int i = nums.length - 1; i >= 0; i--) {
			while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
				r = Math.max(r, stack.pop());
			stack.push(i);
		}
		return r - l > 0 ? r - l + 1 : 0;
	}
}
