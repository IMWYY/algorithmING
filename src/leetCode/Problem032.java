package leetCode;

import java.util.Arrays;
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

    public static void main(String[] args) {
        System.out.println(new Problem032().longestValidParentheses("()(()"));
    }

    /**
     * 利用栈 栈中记录每个(的下标。循环遍历，每次遇到
     * 1.( push
     * 2.) 如果stack为空 重新计数 更新result的值。如果stack不为空，那么计数器增加 具体看注释
     * O(n) time + O(n) time
     */
    public int longestValidParentheses(String s) {
        if (s.isEmpty()) return 0;
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
                    } else {                // 如果栈没空 比较没有被pop的(的下标到当前下表的距离 和 计数器的大小，选取小的更新result
                        int pre = stack.peek();
                        if (temp >= i - pre) {
                            result = Math.max(result, i - pre);
                        }
                    }
                }
            } else {
                stack.push(i);
            }
        }
        return result;
    }

    /**
     * 动态规划
     * dp[i] 表示以i结尾的最长匹配长度
     * dp[i] 只考虑以)结尾的位置 即考虑 ()和)) 结尾两种情况
     */
    public int longestValidParentheses1(String s) {
        if (s.isEmpty()) return 0;
        int[] dp = new int[s.length()];
        int result = 0;
        Arrays.fill(dp, 0);
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {           //()结尾
                    dp[i] = i - 2 >= 0 ? dp[i - 2] : 0;
                } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {      //)) 结尾
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

}
