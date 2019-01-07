package leetCode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * <p>
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * <p>
 * Could you do it without extra space and in O(n) runtime?
 * You may assume the returned list does not count as extra space.
 * <p>
 * Example:
 * <p>
 * Input:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * Output:
 * [5,6]
 * create by stephen on 2018/6/26
 */
public class Problem448 {

    /**
     * 用负数来表示该下标已经存在
     * O(n) space + O(1) space
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            int v = Math.abs(nums[i]);
            if (nums[v - 1] > 0) nums[v - 1] = -nums[v - 1];
        }
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > 0) result.add(i+1);
        }
        return result;
    }
}
