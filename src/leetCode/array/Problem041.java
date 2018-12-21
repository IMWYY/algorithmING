package leetCode.array;

import java.util.Arrays;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,0]
 * Output: 3
 * Example 2:
 * <p>
 * Input: [3,4,-1,1]
 * Output: 2
 * Example 3:
 * <p>
 * Input: [7,8,9,11,12]
 * Output: 1
 * create by stephen on 2018/7/5
 */
public class Problem041 {

	/**
	 * 对于每个数字 如果在数组长度范围内 将其交换放在对应的位置
	 * 然后从前往后依次检查数组的位置
	 * O(n)time + O(1) space
	 */
	public int firstMissingPositive(int[] nums) {
		int i = 0;
		int n = nums.length;
		while (i < n) {
			if (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
				int temp = nums[i];
				nums[i] = nums[nums[i] - 1];
				nums[nums[i] - 1] = temp;
			} else {
				i++;
			}
		}
		for (i = 0; i < n; ++i)
			if (nums[i] != (i + 1))
				return i + 1;
		return n + 1;
	}
}

