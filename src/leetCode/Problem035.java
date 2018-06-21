package leetCode;

/**
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * <p>
 * You may assume no duplicates in the array.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,3,5,6], 5
 * Output: 2
 * create by stephen on 2018/6/21
 */
public class Problem035 {

    public static void main(String[] args) {
        System.out.println(new Problem035().searchInsert(new int[]{1, 3, 3, 5, 6}, 2));
    }

    /**
     * 几种二分查找要区分一下：
     * 等值查找 第一次出现 最后一次出现 第一个比target小的 第一个比target大的
     */
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0) return 0;
        int left = 0, right = nums.length -1;

        while (left <= right) {
            int mid = left + ((right - left)>>1);

            if (nums[left] > target) break;

            if (nums[mid] == target){
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {    // nums[mid] > target
                right = mid;
            }
        }
        return left;
    }
}
