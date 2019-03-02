package leetCode.array;

import java.util.Stack;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 * <p>
 * Example:
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * create by stephen on 2018/5/2
 */
public class Problem042 {

	/**
	 * 使用两个数组记录左边界和右边界 选小的作为水高度
	 * left_max记录到i位置的左边最大值
	 * right_max记录到i位置的右边最大值
	 * O(n）time + O(n) space
	 */
	public int trap(int[] height) {
		if (height == null || height.length <= 2)
			return 0;
		int ans = 0;
		int size = height.length;

		int[] left_max = new int[height.length];
		left_max[0] = height[0];
		for (int i = 1; i < size; i++) {
			left_max[i] = Math.max(height[i], left_max[i - 1]);
		}

		int[] right_max = new int[height.length];
		right_max[size - 1] = height[size - 1];
		for (int i = size - 2; i >= 0; i--) {
			right_max[i] = Math.max(height[i], right_max[i + 1]);
		}

		for (int i = 1; i < size - 1; i++) {
			ans += Math.min(left_max[i], right_max[i]) - height[i];
		}

		return ans;
	}

	/**
	 * 利用栈
	 * 思想：遍历 每次遇到
	 * 1.height[i]比栈顶元素小，就入栈（说明栈顶元素可以作为左边界）
	 * 2.height[i]比栈顶元素大 i就是右边界 计算trap的水量
	 * O(n) time + O(n) space
	 */
	public int trap2(int[] height) {
		int ans = 0, current = 0;
		Stack<Integer> st = new Stack<>();
		while (current < height.length) {
			while (!st.isEmpty() && height[current] > height[st.peek()]) {
				int top = st.peek();
				st.pop();
				if (st.empty())
					break;
				int distance = current - st.peek() - 1;
				int bounded_height = Math.min(height[current], height[st.peek()]) - height[top];
				ans += distance * bounded_height;
			}
			st.push(current++);
		}
		return ans;
	}

	/**
	 * 使用前后两个指针
	 * O(n）time + O(1) space
	 */
	public int trap3(int[] a) {
		int left = 0;
		int right = a.length - 1;
		int res = 0;
		int maxleft = 0, maxright = 0;
		while (left <= right) {
			if (a[left] <= a[right]) {
				if (a[left] >= maxleft)
					maxleft = a[left];
				else
					res += maxleft - a[left];
				left++;
			} else {
				if (a[right] >= maxright)
					maxright = a[right];
				else
					res += maxright - a[right];
				right--;
			}
		}
		return res;
	}
}
