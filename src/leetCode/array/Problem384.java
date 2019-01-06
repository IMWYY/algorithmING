package leetCode.array;

import java.util.Random;

/**
 * Shuffle a set of numbers without duplicates.
 * create by stephen on 2018/10/23
 */
public class Problem384 {
	/**
	 * 参考java源码Collections.shuffle中的算法
	 * Fisher–Yates随机置乱算法
	 */
	class Solution {
		private int[] origin;
		private int[] array;
		private Random random = new Random();

		public Solution(int[] nums) {
			this.origin = nums;
			this.array = nums.clone();
		}

		/**
		 * Resets the array to its original configuration and return it.
		 */
		public int[] reset() {
			array = origin.clone();
			return origin;
		}

		/**
		 * Returns a random shuffling of the array.
		 * 从后往前，每次随机出一个index，将下一个i和index的数值交换
		 * O(n) time + O(n) space
		 */
		public int[] shuffle() {
			for (int i = array.length; i > 0; i--) {
				int index = random.nextInt(i);
				int temp = array[i - 1];
				array[i - 1] = array[index];
				array[index] = temp;
			}
			return array;
		}
	}

}
