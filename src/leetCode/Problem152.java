package leetCode;

/**
 * Given an integer array nums, find the contiguous subarray within an array
 * (containing at least one number) which has the largest product.
 * <p>
 * Example 1:
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * <p>
 * Example 2:
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 * <p>
 * create by stephen on 2018/5/11
 */
public class Problem152 {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{-2, 0, -1}));
    }

    public static int maxProduct(int[] nums) {
        int max = 1, min = 1;
        int result = Integer.MIN_VALUE;

        for (int num : nums) {
            int temp1 = max * num, temp2 = min * num;
            max = Math.max(Math.max(temp1, temp2), num);
            min = Math.min(Math.min(temp1, temp2), num);

            result = Math.max(result, max);
        }

        return result;
    }
}
