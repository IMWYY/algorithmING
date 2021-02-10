package leetCode.twoPoint;

import java.util.Arrays;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * <p>
 * Note:
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 */
public class Problem088 {

	/**
	 * merge from end to the start
	 * O(m+n) time + O(1) space
	 */
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int p1 = m - 1, p2 = n - 1, p = m + n - 1;
		while (p1 >= 0 && p2 >= 0) {
			if (nums1[p1] > nums2[p2]) {
				nums1[p--] = nums1[p1--];
			} else {
				nums1[p--] = nums2[p2--];
			}
		}
		while (p1 >= 0)
			nums1[p--] = nums1[p1--];
		while (p2 >= 0)
			nums1[p--] = nums2[p2--];
	}
}
