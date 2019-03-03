package leetCode.dfsAndbfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid.
 * Return all possible results.
 * <p>
 * Note: The input string may contain letters other than the parentheses ( and ).
 * <p>
 * Example 1:
 * <p>
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * Example 2:
 * <p>
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * create by stephen on 2018/6/19
 */
public class Problem301 {

	/**
	 * 方法1：
	 * 有点太难理解
	 * 参考https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/Easy-Short-Concise-and-Fast-Java-DFS-3-ms-solution/146932
	 * 下的高vote评论的解释
	 * <p>
	 * 只考虑 ）比 (多的情况 对于相反的情况同理
	 */
	public List<String> removeInvalidParentheses(String s) {
		List<String> ans = new ArrayList<>();
		remove(s, ans, 0, 0, new char[] { '(', ')' });
		return ans;
	}

	/**
	 * 维护两个pos 并且所有的处理最后都要reverse一下
	 * 因为判断的时候只看par[1]比par[0]的情况 相反的情况则需要reverse一下后才能判断
	 *
	 * @param s              字符串
	 * @param ans            结果集合
	 * @param startCountPos  开始计算(和)数量的位置 在此位置之前的子字符串已经是valid了
	 * @param startRemovePos 一旦出现invalid的情况，可以删除多余字符的最开始位置
	 * @param par            (和)的位置 用于reverse
	 */
	public void remove(String s, List<String> ans, int startCountPos, int startRemovePos, char[] par) {
		for (int stack = 0, i = startCountPos; i < s.length(); ++i) {
			if (s.charAt(i) == par[0])
				stack++;
			if (s.charAt(i) == par[1])
				stack--;
			if (stack >= 0)
				continue;       // 表明当前 ( 比 ) 多 正常 因为后面还可能会出现 )

			// 表明当前 ) 比 ( 多  需要开始删除 ) 字符
			// 下面的for循环就是找到要删除的 ) 同时如果出现连续的 ) 只能删除第一个 否则会出现重复的结果
			for (int j = startRemovePos; j <= i; ++j) {
				//两种情况
				// 1. s.charAt(j) == par[1] &&  s.charAt(j - 1) != par[1]  检查重复 只移旭重复字符的第一个位置
				// 2. s.charAt(j) == par[1] && j == start_remove_pos 若移除的位置就是开始位置 不需要检查重复
				if (s.charAt(j) == par[1] && (j == startRemovePos || s.charAt(j - 1) != par[1])) {
					// 递归调用start_count_pos还是i, start_remove_pos还是j, 是因为已经删除了一个字符 +1 -1 抵消
					remove(s.substring(0, j).concat(s.substring(j + 1)), ans, i, j, par);
				}
			}
			// 递归调用remove处理了后面的所有字符串 不需要再循环了
			return;
		}

		// 如果遇到 ( 比 ）多的情况 反转字符串和par
		String reversed = new StringBuilder(s).reverse().toString();
		if (par[0] == '(') {// finished left to right
			remove(reversed, ans, 0, 0, new char[] { ')', '(' });  // 所有的字符串都会走一遍这个方法
		} else {  // finished right to left
			ans.add(reversed);   // 走到这里的时候 字符串已经反转再反转 所以就是原顺序了
		}
	}

	/**
	 * 方法2：
	 * 另一种比较简洁的方法 尝试所有的情况
	 */
	public List<String> removeInvalidParentheses1(String s) {
		Set<String> res = new HashSet<>();
		// sanity check
		if (s == null)
			return new ArrayList<>(res);

		Set<String> visited = new HashSet<>();
		Queue<String> queue = new LinkedList<>();
		// initialize
		queue.add(s);
		visited.add(s);

		boolean found = false;

		while (!queue.isEmpty()) {
			s = queue.poll();

			if (isValid(s)) {
				// found an answer, add to the result
				res.add(s);
				found = true;
			}

			if (found)
				// 一旦找到一个合法的字符串 说明已经是找到了最长的合法字符串 不需要在继续for循环移除某一个字符 只需要将队列中剩余的string判断完即可
				continue;

			// generate all possible states
			for (int i = 0; i < s.length(); i++) {
				// we only try to remove left or right paren
				if (s.charAt(i) == '(' || s.charAt(i) == ')') {
					String t = s.substring(0, i).concat(s.substring(i + 1));
					if (!visited.contains(t)) {
						// for each state, if it's not visited, add it to the queue
						queue.add(t);
						visited.add(t);
					}
				}
			}
		}

		return new ArrayList<>(res);
	}

	// helper function checks if string s contains valid parantheses
	private boolean isValid(String s) {
		int count = 0;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(')
				count++;
			if (c == ')' && count-- == 0)
				return false;
		}

		return count == 0;
	}
}
