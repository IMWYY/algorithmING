package leetCode.dynamicProgramming;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 * <p>
 * Example 1:
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * <p>
 * Example 2:
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 * <p>
 * create by stephen on 2018/5/22
 */
public class Problem032 {

	/**
	 * 方法一：动态规划
	 * dp[i] 表示以i结尾的最长合法匹配长度，而最长合法子串的长度就是max(dp[i]) for i=0...n
	 * dp[i] 只考虑以)结尾的位置 即考虑 ()和)) 结尾两种情况
	 * O(n) time + O(n) time
	 */
	public int longestValidParentheses(String s) {
		int maxans = 0;
		int dp[] = new int[s.length()];
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == ')') {
				if (s.charAt(i - 1) == '(') {
					dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
				} else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
					dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
				}
				maxans = Math.max(maxans, dp[i]);
			}
		}
		return maxans;
	}

	/**
	 * 方法二：利用栈 栈中记录每个(的下标。循环遍历，每次遇到
	 * 1.( push
	 * 2.) 如果stack为空（此时这个右括号是多余的）则重新计数，更新result的值。如果stack不为空，那么计数器增加 具体看注释
	 * O(n) time + O(n) time
	 */
	public int longestValidParentheses1(String s) {
		if (s.isEmpty())
			return 0;
		Stack<Integer> stack = new Stack<>();
		int result = 0, temp = 0;

		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if (c == ')') {
				if (stack.isEmpty()) {
					result = Math.max(result, temp);
					temp = 0;               // 重新计数
				} else {
					stack.pop();
					temp += 2;
					if (stack.isEmpty()) {  // 如果栈空了 直接更新result
						result = Math.max(result, temp);
					} else {
						// 如果栈没空 没有被pop的(的下标到当前下表的距离为合法字符串
						result = Math.max(result, i - stack.peek());
					}
				}
			} else {
				stack.push(i);
			}
		}
		return result;
	}

}
