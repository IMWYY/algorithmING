package leetCode.array;

import java.util.Arrays;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 */
public class Problem189 {

	/**
	 * Cyclic Replacements
	 * O(n) time + O(1) space
	 */
	public void rotate(int[] nums, int k) {
		k = k % nums.length;
		int count = 0;
		for (int start = 0; count < nums.length; start++) {
			int current = start;
			int prev = nums[start];
			do {
				int next = (current + k) % nums.length;
				int temp = nums[next];
				nums[next] = prev;
				prev = temp;
				current = next;
				count++;
			} while (start != current);
		}
	}

	/**
	 * Reverse array
	 * O(n) time + O(1) space
	 */
	public void rotate1(int[] nums, int k) {
		k %= nums.length;
		reverse(nums, 0, nums.length - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, nums.length - 1);
	}
	private void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
	}

}
