package leetCode.array;

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
     * 二分查找 只要找到子结构即可
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        if (nums[0] == target) return 0;
        int left = 0, right = nums.length - 1, mid;
        while (left < right) {
            if (nums[left] == target) return left;
            if (nums[right] == target) return right;
            mid = (left + right) / 2;
            if (nums[mid] == target) return mid;

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
