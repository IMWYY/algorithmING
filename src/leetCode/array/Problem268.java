package leetCode.array;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
 * find the one that is missing from the array.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,0,1]
 * Output: 2
 * Example 2:
 * <p>
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * create by stephen on 2018/7/2
 */
public class Problem268 {

    /**
     * 巧妙利用异或
     * 其他方法：排序/HashSet/利用高斯求和共识
     */
    public int missingNumber(int[] nums) {
        int xor = nums.length;
        for (int i=0; i<nums.length; ++i) {
            xor ^= (i ^ nums[i]);
        }
        return xor;
    }
}
