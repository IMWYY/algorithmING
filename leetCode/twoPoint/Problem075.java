package leetCode.twoPoint;

/**
 * Given an array with n objects colored red, white or blue, sort them in-place so
 * that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * <p>
 * Note: You are not suppose to use the library's sort function for this problem.
 * Could you come up with a one-pass algorithm using only constant space?
 */
public class Problem075 {

	public void sortColors(int[] nums) {
		int low = 0, high = nums.length - 1;
		int i = 0, temp;
		while (low <= high && i <= high) {
			if (nums[i] == 2) { // swap i with high
				temp = nums[high];
				nums[high] = nums[i];
				nums[i] = temp;
				high--;
			} else if (nums[i] == 0) { // swap i with low
				temp = nums[low];
				nums[low] = nums[i];
				nums[i] = temp;
				low++;
				i++;
			} else if (nums[i] == 1) {
				i++;
			}
		}
	}

	/**
	 * counting sort
	 */
	public void sortColors2(int[] nums) {
		int a = 0, b = 0, c = 0;
		for (int num : nums) {
			if (num == 0)
				a++;
			if (num == 1)
				b++;
			if (num == 2)
				c++;
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

}
