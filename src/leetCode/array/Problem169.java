package leetCode.array;

import java.util.Arrays;

/**
 * Given an array of size n, find the majority element.
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * <p>
 * You may assume that the array is non-empty and the majority element always exist in the array.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,3]
 * Output: 3
 * Example 2:
 * <p>
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 * create by stephen on 2018/6/26
 */
public class Problem169 {
    /**
     * 用一个count记录当前出现的次数
     * 遇到不等于该candidate的数字就减一 否则加一
     * 当count为0的时候更换新的candidate
     * O(n) time + O(1) space
     */
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;
        for (int i=1; i<nums.length; ++i) {
            if (count == 0){
                candidate = nums[i];
            }
            if (nums[i] == candidate) {
                count ++;
            } else {
                count --;
            }
        }
        return candidate;
    }

    /**
     * 排序 取中间
     * O(nlogn) time + O(1) space
     */
    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}
