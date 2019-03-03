package leetCode.twoPoint;

import java.util.Arrays;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * <p>
 * Note:
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * <p>
 * Example:
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * Output: [1,2,2,3,5,6]
 * create by stephen on 2018/7/4
 */
public class Problem088 {

	/**
	 * 数组合并从后往前合并
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

	/**
	 * 额外拷贝一个数组
	 * O(m+n) time + O(m) space
	 */
	public void merge1(int[] nums1, int m, int[] nums2, int n) {
		int[] temp = Arrays.copyOf(nums1, nums1.length);
		int pTemp = 0, p2 = 0, p1 = 0;
		while (pTemp < m && p2 < n) {
			if (temp[pTemp] <= nums2[p2]) {
				nums1[p1++] = temp[pTemp++];
			} else {
				nums1[p1++] = nums2[p2++];
			}
		}
		while (pTemp < m)
			nums1[p1++] = temp[pTemp++];
		while (p2 < n)
			nums1[p1++] = nums2[p2++];
	}
}
