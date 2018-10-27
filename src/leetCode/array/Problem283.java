package leetCode.array;

/**
 * Given an array nums, write a function to move all 0's to the end of it
 * while maintaining the relative order of the non-zero elements.
 * <p>
 * Example:
 * <p>
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 * <p>
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 * create by stephen on 2018/6/26
 */
public class Problem283 {

    /**
     * O(n) space + o(1) space
     */
    public void moveZeroes(int[] nums) {
        for (int lastNonZeroFoundAt = 0, cur = 0; cur < nums.length; cur++) {
            if (nums[cur] != 0) {
                int temp = nums[cur];
                nums[cur] = nums[lastNonZeroFoundAt];
                nums[lastNonZeroFoundAt] = temp;
                lastNonZeroFoundAt ++;
            }
        }
    }

    /**
     * O(n) space + o(1) space
     */
    public void moveZeroes1(int[] nums) {
        int noneZeroIndex = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                nums[noneZeroIndex++] = nums[i];
            }
        }
        for (int i=noneZeroIndex; i<nums.length; ++i) {
            nums[i] = 0;
        }
    }
}
