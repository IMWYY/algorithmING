package leetCode.array;

/**
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * Formally the function should:
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
 */
public class Problem334 {
	/**
	 * 比较巧妙 用两个small 和 middle记录到当前下标位置的最小值和次小值
	 * 如果找到一个数比这两个数都大 那么就存在一个递增的三元组
	 * O(n) time + O(1) space
	 */
	public boolean increasingTriplet(int[] nums) {
		// start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
		int small = Integer.MAX_VALUE, middle = Integer.MAX_VALUE;
		for (int n : nums) {
			if (n <= small)
				small = n;  // update small if n is smaller than both
			else if (n <= middle)
				middle = n;  // update middle only if greater than small but smaller than middle
			else
				return true; // return if you find a number bigger than both
		}
		return false;
	}
}
