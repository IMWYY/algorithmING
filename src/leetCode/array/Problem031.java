package leetCode.array;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place and use only constant extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * <p>
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * <p>
 * create by stephen on 2018/5/9
 */
public class Problem031 {

    /**
     * 从后往前找到第一个 nums[i] > nums[i - 1]
     * 从i以后找最贴近 nums[i - 1] 且比 nums[i - 1] 大的数字和nums[i - 1] 交换
     * 然后将i以后的数字升序排列
     */
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) return;

        //从后往前找到第一个 nums[i] > nums[i - 1]
        int tempIndex = -1;
        for (int i = nums.length - 1; i > 0; --i) {
            if (nums[i] > nums[i - 1]) {
                tempIndex = i - 1;
                break;
            }
        }

        if (tempIndex == -1) {
            Arrays.sort(nums);
            return;
        }

        // 从i以后找最贴近 nums[i - 1] 且比 nums[i - 1] 大的数字和nums[i - 1] 交换
        int chosenIndex = tempIndex + 1;
        for (int i = tempIndex + 1; i < nums.length; ++i) {
            if (nums[i] > nums[tempIndex]) {
                chosenIndex = i;
            }
        }

        int temp = nums[chosenIndex];
        nums[chosenIndex] = nums[tempIndex];
        nums[tempIndex] = temp;
        //将i以后的数字升序排列
        Arrays.sort(nums, tempIndex + 1, nums.length);
    }
}
