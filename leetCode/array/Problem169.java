package leetCode.array;

import java.util.Arrays;

/**
 * Given an array of size n, find the majority element.
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */
public class Problem169 {
    // counter to record the number of ocurrence of candidate elements
    // O(n) time + O(1) space
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;
        for (int i=1; i<nums.length; ++i) {
            if (count == 0)candidate = nums[i];
            if (nums[i] == candidate) count ++;
            else count --;
        }
        return candidate;
    }

    // sort and then return the middle element
    // O(nlogn) time + O(1) space
    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}
