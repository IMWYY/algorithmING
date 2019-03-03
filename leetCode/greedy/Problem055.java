package leetCode.greedy;

import java.util.Arrays;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Determine if you are able to reach the last index.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 * jump length is 0, which makes it impossible to reach the last index.
 * <p>
 * create by stephen on 2018/6/30
 */
public class Problem055 {

    /**
     * 贪心算法
     * 循环找到可以到达终点的最左位置 如果最后lastPos=0 则可以到达重点
     * O(n) time + O(n) space
     */
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    /**
     * 动态规划 从后往前
     */
    public boolean canJump1(int[] nums) {
        boolean[] memo = new boolean[nums.length];
        Arrays.fill(memo, false);
        memo[memo.length - 1] = true;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j]) {
                    memo[i] = true;
                    break;
                }
            }
        }

        return memo[0];
    }

    /**
     * 动态规划
     * 从前向后遍历 将所有可以到达的下标设为true
     * O(n2) time + O(n) space
     */
    public boolean canJump2(int[] nums) {
        if (nums.length <= 1) return true;
        boolean[] reach = new boolean[nums.length];
        Arrays.fill(reach, false);
        reach[0] = true;
        for (int i = 0; i < nums.length; ++i) {
            if (!reach[i]) continue;        // 只能从可以到达的位置算
            int canReach = i + nums[i];
            if (i + nums[i] >= nums.length - 1) return true;
            for (int j = i + 1; j <= canReach; ++j) {
                reach[j] = true;
            }
        }
        return reach[nums.length - 1];
    }


}
