package leetCode;

import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 * <p>
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * <p>
 * Example:
 * Input: [2,1,5,6,2,3]
 * Output: 10
 * create by stephen on 2018/5/6
 */
public class Problem084 {
    /**
     * 利用stack：
     * 如果栈为空或者当前bar大于栈顶元素，入栈
     * 否则出栈作为高度 找到左下标计算面积
     * 最后返回最大的面积
     *
     * 每次遇到高度下降的情况就计算当前位置（i）之前的所有比当前高度大的高度面积
     * （右下标是i，出栈找到左下标，高度为栈顶元素对应的高度）
     * O(n) time + O(n) space
     */
    @SuppressWarnings("all")
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) return 0;
        if (heights.length == 1) return heights[0];
        int res = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= heights.length; ) {
            int bar = i == heights.length ? 0 : heights[i];
            if (stack.isEmpty() || bar >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int h = heights[stack.pop()];
                while (!stack.isEmpty() && h == heights[stack.peek()]) {
                    stack.pop();
                }
                int left = stack.isEmpty() ? 0 : stack.peek() + 1; //计算的是peek后面一位作为左下标
                res = Math.max(res, h * (i - left));
            }
        }
        return res;
    }

    /**
     * 暴力解法：计算以每个下标结尾的最大面积 然后取所有面积的最大值
     * O(n2) time + O(1) space
     */
    public int largestRectangleArea1(int[] heights) {
        if (heights.length == 0) return 0;
        if (heights.length == 1) return heights[0];
        int res = 0;
        for (int i = 0; i < heights.length; ++i) {
            int temp = heights[i], bound = heights[i];
            for (int j = i - 1; j >= 0; --j) {
                bound = Math.min(heights[j], bound);
                temp = Math.max(temp, bound * (i - j + 1));
            }
            res = Math.max(res, temp);
        }
        return res;
    }
}
