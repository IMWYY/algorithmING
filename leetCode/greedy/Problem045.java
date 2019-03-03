package leetCode.greedy;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * Example:
 * Input: [2,3,1,1,4]
 * Output: 2
 * <p>
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * <p>
 * Note:You can assume that you can always reach the last index.
 * <p>
 * create by stephen on 2019/2/6
 */
public class Problem045 {

    public static void main(String[] args) {
        System.out.println(jump(new int[]{2, 3, 1, 1, 4}));
    }

    /**
     * BFS的变形 每一次jump到一个点 可以决定一个下一次jump可以到达的区间
     * 比如 {2, 3, 1, 1, 4}
     * 第0次jump：[0]
     * 第1次jump: [1, 2]
     * 第2jump: [3, 4]
     */
    public static int jump(int[] nums) {
        if (nums.length == 0) return 0;
        int count = 0, curLeft = 0, curRight = 0;
        while (curRight < nums.length - 1) {
            int nextRight = 0;
            // find next right
            for (int i = curLeft; i <= curRight; ++i) {
                nextRight = Math.max(nextRight, i + nums[i]);
            }
            count++;
            curLeft = curRight + 1;
            curRight = nextRight;
        }
        return count;
    }
}
