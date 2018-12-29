package leetCode.array;

import java.util.Arrays;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3,4,5,6,7] and k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * <p>
 * Note:
 * <p>
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 * <p>
 * create by stephen on 2018/7/5
 */
public class Problem189 {

	/**
	 * 循环替换：我们可以把后面的数字移到前面来 但是前面的数字就被覆盖了
	 * 所以这个方法的思想是将所有的数字都循环移动到它正确的位置 直到不能移动为止
	 * 见https://leetcode.com/problems/rotate-array/solution/ 图片解释
	 * <p>
	 * 这里还需要注意的是 利用一个count来记录当前已经被挪动过位置的数
	 * 当count=nums.length的时候 就表示结束了
	 * <p>
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
	 * 利用额外一个数组
	 * O(n) time + O(n) space
	 */
	public void rotate2(int[] nums, int k) {
		if (nums.length <= 1 || k == 0)
			return;
		k = k % nums.length;
		int[] temp = Arrays.copyOf(nums, nums.length);
		System.arraycopy(temp, temp.length - k, nums, 0, k);
		System.arraycopy(temp, 0, nums, k, nums.length - k);
	}

	/**
	 * 反转数组 先整体反转数组 再反转前半部分 再反转后半部分
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
