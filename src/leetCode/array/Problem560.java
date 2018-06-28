package leetCode.array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given an array of integers and an integer k, you need to
 * find the total number of continuous subarrays whose sum equals to k.
 * <p>
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * <p>
 * create by stephen on 2018/5/1
 */
public class Problem560 {

    /**
     * 遍历一遍 利用HashMap记录sum的对应出现的次数
     * 每次操作map查看sum-k是否在map中 count加上出现的次数
     * 举例说明：
     * 加入当前下标为i，sum(i)-k在map中说明，之前的某个位置j sum(j) = sum(i)-k
     * 也即 sum(i)-sum(j) = k 就是 i-j的和等于k
     * O(n) time + O(n) space
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    /**
     * 动态规划解法
     * O(n2) time + O(n) space
     */
    public int subarraySum1(int[] nums, int k) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 0);
        dp[0] = k == nums[0] ? 1 : 0;
        int temp;
        for (int i = 1; i < nums.length; ++i) {
            temp = 0;
            dp[i] += dp[i - 1];
            for (int j = i; j >= 0; j--) {
                temp += nums[j];
                if (temp == k) dp[i]++;
            }
        }

        return dp[nums.length - 1];
    }


    /**
     * 记录类加和 那么[i,j]之间的和为sum[j]-sum[i]
     * O(n2) time + O(1) space
     */
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                if (sum[end] - sum[start] == k)
                    count++;
            }
        }
        return count;
    }

    /**
     * 不用预处理 直接比较
     * O(n2) time + O(1) space
     */
    public int subarraySum3(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }


}
