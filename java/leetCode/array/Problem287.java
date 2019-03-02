package leetCode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
 * find the duplicate one.
 * <p>
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 * <p>
 * create by stephen on 2018/4/26
 */
public class Problem287 {

    /**
     * Floyd's Tortoise and Hare 链表闭环判断算法
     * 将数组铺平，类似于循环链表，即寻找环的起点
     * 满足不改动数字且O(1) space
     * O(n) time + O(1) space
     */
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        // 相遇后 fast=a+(t+k)b  slow=a+tb
	    // 那么新结点从头开始走 a之后，与slow相差刚好的b的整数倍，两者相遇在环的起点

        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /**
     * 在O(1)space 且不能改动数组的要求下 最普通的想法
     * O(n^2) time + O(1) space
     */
    public int findDuplicate1(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[j] == nums[i]) return nums[i];
            }
        }
        return -1;
    }

    /**
     * 排序 O(nlogn) time + O(1) space
     */
    public int findDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }

        return -1;
    }


    /**
     * 利用集合或者map O(n) time + O(n) space
     */
    public int findDuplicate3(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return num;
            }
            seen.add(num);
        }

        return -1;
    }

}
