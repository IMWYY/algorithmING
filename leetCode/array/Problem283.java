package leetCode.array;

/**
 * Given an array nums, write a function to move all 0's to the end of it
 * while maintaining the relative order of the non-zero elements.
 */
public class Problem283 {

    /**
     * O(n) space + o(1) space
     */
    public void moveZeroes1(int[] nums) {
        int noneZeroIndex = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) nums[noneZeroIndex++] = nums[i];
        }
        for (int i=noneZeroIndex; i<nums.length; ++i) nums[i] = 0;
    }
}
