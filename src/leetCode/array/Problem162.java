package leetCode.array;

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

    public static void main(String[] args) {
        System.out.println(new Problem162().findPeakElement(new int[]{1, 2, 3}));
    }

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

    public int findPeakElement1(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            if ((i == 0 && i + 1 < nums.length && nums[i] > nums[i + 1])
                    || (i == nums.length - 1 && i - 1 >= 0 && nums[i] > nums[i - 1])
                    || (i != 0 && i != nums.length - 1 && nums[i] > nums[i - 1] && nums[i] > nums[i + 1])) {
                return i;
            }
        }
        return 0;
    }


}
