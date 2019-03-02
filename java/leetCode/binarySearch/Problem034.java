package leetCode.binarySearch;

/**
 * Given an array of integers nums sorted in ascending order,
 * find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * <p>
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * <p>
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * <p>
 * create by stephen on 2018/5/5
 */
public class Problem034 {

    /**
     * 找到target第一次和最后一次出现的位置即可
     */
    public static int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};
        int left = 0, right = nums.length - 1, first;

        //找到第一次出现的位置
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if (nums[left] != target) {
            return new int[]{-1, -1};
        }
        first = left;

        left = 0;
        right = nums.length - 1;

        //找到最后一次出现的位置
        while (left < right) {
            int mid = right - ((right - left) >> 1);
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return new int[]{first, left};
    }
}
