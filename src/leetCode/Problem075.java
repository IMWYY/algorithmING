package leetCode;

/**
 * Given an array with n objects colored red, white or blue, sort them in-place so
 * that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * <p>
 * Note: You are not suppose to use the library's sort function for this problem.
 * <p>
 * Example:
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * <p>
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's,
 * then 1's and followed by 2's.
 * Could you come up with a one-pass algorithm using only constant space?
 * <p>
 * create by stephen on 2018/5/1
 */
public class Problem075 {

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        new Problem075().sortColors(nums);

    }

    /**
     * 计数排序 遍历两遍
     */
    public void sortColors(int[] nums) {
        int a = 0, b = 0, c = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) a++;
            if (nums[i] == 1) b++;
            if (nums[i] == 2) c++;
        }

        for (int i = 0; i < nums.length; ++i) {
            if (a > 0) {
                nums[i] = 0;
                a--;
            } else if (b > 0) {
                nums[i] = 1;
                b--;
            } else if (c > 0) {
                nums[i] = 2;
                c--;
            }
        }
    }

    /**
     * 遍历一遍 前后两个指针一起往中间移动
     */
    public void sortColors2(int[] nums) {
        int low = 0, high = nums.length - 1;
        int i = 0, temp = 0;
        while (low <= high && i <= high) {
            if (nums[i] == 2) {
                temp = nums[high];
                nums[high] = nums[i];
                nums[i] = temp;
                high --;
            } else if (nums[i] == 0) {
                temp = nums[low];
                nums[low] = nums[i];
                nums[i] = temp;
                low ++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            }
        }
    }
}
