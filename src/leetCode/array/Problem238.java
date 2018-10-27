package leetCode.array;

/**
 * Given an array nums of n integers where n > 1,  return an array output such that output[i]
 * is equal to the product of all the elements of nums except nums[i].
 * <p>
 * Example:
 * <p>
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Note: Please solve it without division and in O(n).
 * <p>
 * Follow up:
 * Could you solve it with constant space complexity?
 * (The output array does not count as extra space for the purpose of space complexity analysis.)
 * create by stephen on 2018/6/26
 */
public class Problem238 {

    /**
     * 将乘积分成两部分 该数字之前和该数字之后
     * 用一个数组记录从后往前的乘积
     * O(n) time + O(n) space
     */
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        int[] postProduct = new int[nums.length];

        int temp = nums[nums.length-1];
        postProduct[nums.length-1] = 1;
        for (int i=nums.length-2; i>=0; --i) {
            postProduct[i] = temp;
            temp *= nums[i];
        }

        temp = 1;
        for (int i=0; i<nums.length; ++i) {
            result[i] = temp * postProduct[i];
            temp *= nums[i];
        }
        return result;
    }
}
