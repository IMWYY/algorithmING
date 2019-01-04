package leetCode.array;

import java.util.Arrays;
import java.util.Random;

/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1, 5, 1, 1, 6, 4]
 * Output: One possible answer is [1, 4, 1, 5, 1, 6].
 * Example 2:
 * <p>
 * Input: nums = [1, 3, 2, 2, 3, 1]
 * Output: One possible answer is [2, 3, 1, 3, 1, 2].
 * create by stephen on 2018/10/9
 */
public class Problem324 {

	/**
	 * https://leetcode.com/problems/wiggle-sort-ii/discuss/77684/Summary-of-the-various-solutions-to-Wiggle-Sort-for-your-reference
	 * <p>
	 * 可以证明 局部的大小关系经过转换后可以得到一个全局的大小关系：odd index的数字不小于even index的数字
	 * 所以将数组排序后，分成两部分，小数字部分放到even index，大数字部分放到odd index就可以了
	 * j = 2 * (m - 1 - i) if i < m
	 * j = 2 * (n - 1 - i) + 1 if i >= m
	 * <p>
	 * 但是需要考虑数字相等的情况，不能让相等的数组相邻，
	 * 所以在放的时候
	 * 对于小数字（排序数组的前半部分），将较大的数字放在index小的地方
	 * 对于大数字（排序数组的后半部分），将较小的数字放在index大的地方
	 * 这样相等的部分就不会相邻了
	 * O(nlogn) time and O(n) space
	 */
	public void wiggleSort1(int[] nums) {
		int n = nums.length, m = (n + 1) >> 1;
		int[] copy = Arrays.copyOf(nums, n);
		Arrays.sort(copy);

		for (int i = m - 1, j = 0; i >= 0; i--, j += 2) {
			nums[j] = copy[i];   // 大的数字放前面
		}
		for (int i = n - 1, j = 1; i >= m; i--, j += 2) {
			nums[j] = copy[i];   //小的数字放后面
		}
	}

	/**
	 * 其实并不需要将整个数组全部排序
	 * 只需要找到最中间的pivot 将其partition 分出大小两部分数即可
	 * 优化寻找中间数字的过程 参考Problem215 同时使用一个额外数组
	 * O(n) time and O(n) space
	 */
	public void wiggleSort2(int[] nums) {
		int n = nums.length, m = (n + 1) >> 1;
		int[] copy = Arrays.copyOf(nums, n);
		int median = kthSmallestNumber(nums, m);

		for (int i = 0, j = 0, k = n - 1; j <= k; ) {
			if (copy[j] < median) {
				swap(copy, i++, j++);
			} else if (copy[j] > median) {
				swap(copy, j, k--);
			} else {
				j++;
			}
		}

		for (int i = m - 1, j = 0; i >= 0; i--, j += 2)
			nums[j] = copy[i];
		for (int i = n - 1, j = 1; i >= m; i--, j += 2)
			nums[j] = copy[i];
	}

	/**
	 * 优化寻找中间数字的过程 参考Problem215
	 * O(n) time and O(1) space
	 */
	public void wiggleSort3(int[] nums) {
		int n = nums.length, m = (n + 1) >> 1;
		// 采用Problem215的最优算法 O(n) time and O(1) space
		int median = kthSmallestNumber(nums, m);

		for (int i = 0, j = 0, k = n - 1; j <= k; ) {
			if (nums[A(j, n)] > median) {
				swap(nums, A(i++, n), A(j++, n));
			} else if (nums[A(j, n)] < median) {
				swap(nums, A(j, n), A(k--, n));
			} else {
				j++;
			}
		}
	}

	private int A(int i, int n) {
		return (2 * i + 1) % (n | 1);
	}

	//randomized quick-sort subroutine
	private int kthSmallestNumber(int[] nums, int k) {
		Random random = new Random();
		for (int i = nums.length - 1; i >= 0; i--) {
			swap(nums, i, random.nextInt(i + 1));
		}
		int l = 0, r = nums.length - 1;
		k--;
		while (l < r) {
			int m = getMiddle(nums, l, r);
			if (m < k) {
				l = m + 1;
			} else if (m > k) {
				r = m - 1;
			} else {
				break;
			}
		}
		return nums[k];
	}

	// 将所有比nums[l]小的移到左边 比nums[l]大的移到右边
	private int getMiddle(int[] nums, int l, int r) {
		int i = l;
		for (int j = l + 1; j <= r; j++) {
			if (nums[j] < nums[l])
				swap(nums, ++i, j);
		}
		swap(nums, l, i);
		return i;
	}

	private void swap(int[] nums, int i, int j) {
		int t = nums[i];
		nums[i] = nums[j];
		nums[j] = t;
	}
}
