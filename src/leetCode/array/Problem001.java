package leetCode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * Example:
 * <p>
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * create by stephen on 2018/6/29
 */
public class Problem001 {

    /**
     * 最容易想到的是两次遍历 一遍记录到hashmap中，一遍检查每个位置target - nums[i]是否存在
     * 但是这里不需要两次遍历
     * 只需要一边将数字存入hashmap中，一边检查target - nums[i]是否存在
     * 举例来说：[2, 7, 11, 15]
     * 遍历到2的时候，不存在7；但是遍历7的时候，2已经存入了hashmap，就找到了结果。
     *
     * 遍历两次相当于2+7和7+2都考虑了，而遍历一次只用考虑2+7或者7+2
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[]{0, 0};
    }
}
