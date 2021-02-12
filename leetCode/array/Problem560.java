package leetCode.array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given an array of integers and an integer k, you need to
 * find the total number of continuous subarrays whose sum equals to k.
 */
public class Problem560 {

	/**
	 * optimized with HashMap
	 * O(n) time + O(n) space
	 */
	public int subarraySum(int[] nums, int k) {
		int count = 0, sum = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		for (int num : nums) {
			sum += num;
			if (map.containsKey(sum - k))
				count += map.get(sum - k);
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return count;
	}

	/**
	 * brute force
	 * O(n2) time + O(1) space
	 */
	public int subarraySum1(int[] nums, int k) {
		int count = 0;
		for (int start = 0; start < nums.length; start++) {
			int sum = 0;
			for (int end = start; end < nums.length; end++) {
				sum += nums[end];
				if (sum == k)
					count++;
			}
		}
		return count;
	}

}
