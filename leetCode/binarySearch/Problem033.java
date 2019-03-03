package leetCode.binarySearch;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * <p>
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * <p>
 * create by stephen on 2018/5/4
 */
public class Problem033 {

	/**
	 * 首先找出数组的 拐 点
	 * 之后按照正常的二分查找 不过有bias，这个bias就是拐点和起始位置的偏差
	 */
	public int search(int[] nums, int target) {
		int start = 0;
		int end = nums.length - 1;
		//找出最小值的数组下标
		while (start < end) {
			int mid = (start + end) / 2;
			if (nums[mid] > nums[end]) {
				start = mid + 1; // 每次只把start往后移动
			} else {
				end = mid;
			}
		}
		int bias = start;
		//找出最大值的数组下标
		//		while (start < end) {
		//			int mid = Math.round(((float) start + end) / 2);
		//			if (nums[mid] < nums[start]) {
		//				end = mid - 1; // 每次只移动
		//			} else {
		//				start = mid;
		//			}
		//
		//		}
		int n = nums.length;
		bias = (start + n) - (n - 1); //得到偏移
		start = 0;
		end = nums.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;//中间的位置
			int mid_change = (mid + bias) % nums.length;//中间的位置对应的数组下标
			int value = nums[mid_change];//中间位置的值
			if (target == value) {
				return mid_change;
			}
			if (target < value) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return -1;
	}

	/**
	 * 二分查找 只要找到子结构即可
	 */
	public int search1(int[] nums, int target) {
		if (nums.length == 0)
			return -1;
		if (nums[0] == target)
			return 0;
		int left = 0, right = nums.length - 1, mid;
		while (left < right) {
			if (nums[left] == target)
				return left;
			if (nums[right] == target)
				return right;
			mid = (left + right) / 2;
			if (nums[mid] == target)
				return mid;

			if (nums[left] < target) { //target在数字大的半边
				if (nums[mid] > nums[left]) {       //中间数字在大的半边
					if (nums[mid] > target) {
						right = mid;
					} else {
						left = mid;
					}
				} else {                    //中间数字在小的半边
					right = mid;
				}
			} else {            //target在数字小的半边
				if (nums[mid] > nums[left]) {       //中间数字在大的半边
					left = mid;
				} else {                    //中间数字在小的半边
					if (nums[mid] < target) {
						left = mid;
					} else {
						right = mid;
					}
				}
			}
		}

		return -1;
	}
}
