package leetCode.binarySearch;

/**
 * A peak element is an element that is greater than its neighbors.
 * <p>
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * <p>
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p>
 * You may imagine that nums[-1] = nums[n] = -∞.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * create by stephen on 2018/7/2
 */
public class Problem162 {

    /**
     * 找子结构 二分查找
     */
    public int findPeakElement(int[] nums) {
        if (nums.length <= 1) return 0;
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + ((end - start) >> 1);
            // mid + 1 必定小于nums.length
            if (nums[mid] > nums[mid + 1]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

}
