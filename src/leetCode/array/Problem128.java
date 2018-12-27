package leetCode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * Your algorithm should run in O(n) complexity.
 * <p>
 * Example:
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * <p>
 * create by stephen on 2018/5/1
 */
public class Problem128 {

	/**
	 * 排序后遍历
	 * O(nlgn) time + O(1) space
	 */
	public static int longestConsecutive(int[] nums) {
		if (nums.length <= 1)
			return nums.length;
		Arrays.sort(nums);
		int len = 1, result = 1;
		for (int i = 1; i < nums.length; ++i) {
			if (nums[i] - nums[i - 1] == 1) {
				len++;
				result = Math.max(result, len);
			} else if (nums[i] != nums[i - 1]) {
				len = 1;
			}
		}

		return result;
	}

	/**
	 * HashSet方法
	 * O(n) time + O(n) space
	 */
	public int longestConsecutive1(int[] nums) {
		Set<Integer> num_set = new HashSet<>();
		for (int num : nums) {
			num_set.add(num);
		}

		int longestStreak = 0;

		for (int num : num_set) {
			if (!num_set.contains(num - 1)) {
				int currentNum = num;
				int currentStreak = 1;

				while (num_set.contains(currentNum + 1)) {
					currentNum += 1;
					currentStreak += 1;
				}

				longestStreak = Math.max(longestStreak, currentStreak);
			}
		}

		return longestStreak;
	}
}
