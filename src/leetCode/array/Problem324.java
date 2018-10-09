package leetCode.array;

import java.util.Arrays;

/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1, 5, 1, 1, 6, 4]
 * Output: One possible answer is [1, 4, 1, 5, 1, 6].
 * Example 2:
 * <p>
 * Input: nums = [1, 3, 2, 2, 3, 1]
 * Output: One possible answer is [2, 3, 1, 3, 1, 2].
 * create by stephen on 2018/10/9
 */
public class Problem324 {

    /**
     * https://leetcode.com/problems/wiggle-sort-ii/discuss/77684/Summary-of-the-various-solutions-to-Wiggle-Sort-for-your-reference
     * <p>
     * 可以证明 局部的大小关系经过转换后可以得到一个全局的大小关系：odd index的数字不小于even index的数字
     * 所以将数组排序后，分成两部分，小数字部分放到even index，大数字部分放到odd index就可以了
     * <p>
     * 但是需要考虑数字相等的情况，不能让相等的数组相邻，
     * 所以在放的时候，对于小数字部分，将较大的数字放在index小的地方；对于大数字部分，将较小的数字放在index大的地方，这样相等的部分就不会相邻了
     */
    public void wiggleSort(int[] nums) {
        int n = nums.length, m = (n + 1) >> 1;
        int[] copy = Arrays.copyOf(nums, n);
        Arrays.sort(copy);

        for (int i = m - 1, j = 0; i >= 0; i--, j += 2) {
            nums[j] = copy[i];
        }
        for (int i = n - 1, j = 1; i >= m; i--, j += 2) {
            nums[j] = copy[i];
        }
    }
}
