package leetCode.array;

import java.util.Arrays;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,0]
 * Output: 3
 * Example 2:
 * <p>
 * Input: [3,4,-1,1]
 * Output: 2
 * Example 3:
 * <p>
 * Input: [7,8,9,11,12]
 * Output: 1
 * create by stephen on 2018/7/5
 */
public class Problem041 {

    public static void main(String[] args) {
        System.out.println(new Problem041().firstMissingPositive(new int[]{0, 2, 2, 1, 1}));
    }

    /**
     * 对于每个数字 如果在数组长度范围内 将其交换放在对应的位置
     * 然后从前往后依次检查数组的位置
     * O(n)time + O(1) space
     */
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[i];
                nums[i] = nums[nums[i]-1];
                nums[nums[i]-1] = temp;
            } else {
                i++;
            }
        }
        for (i = 0; i < n; ++i)
            if (nums[i] != (i + 1)) return i + 1;
        return n + 1;
    }

    /**
     * 排序做法 记录第一个正数的位置
     * 如果第一个正数大于1 那么直接返回
     * 否则一次检查后面的正数是否按顺序 需要数字重复的情况
     * O(nlogn)time + O(1) space
     */
    public int firstMissingPositive1(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int posStart = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > 0) {
                if (posStart == -1) {
                    posStart = i;
                    if (nums[i] > 1) return 1;
                } else {
                    if (nums[i] == nums[i - 1]) {
                        posStart++;
                    } else if (nums[i] != i - posStart + 1) {
                        return i - posStart + 1;
                    }
                }
            }
        }
        if (posStart == -1) {
            return 1;
        } else {
            return nums.length - posStart + 1;
        }
    }
}
