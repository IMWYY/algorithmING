package leetCode.dfsAndbfs;

import java.util.ArrayList;
import java.util.List;

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
     * 有点太难理解
     * 参考https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/Easy-Short-Concise-and-Fast-Java-DFS-3-ms-solution/146932
     * 下的高vote评论的解释
     * <p>
     * 只考虑 ）比 (多的情况 对于相反的情况同理
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    public void remove(String s, List<String> ans, int startCountPos, int startRemovePos, char[] par) {
        for (int stack = 0, i = startCountPos; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;       // 表明当前 ( 比 ) 多 正常 因为后面还可能会出现 )

            // 表明当前 ) 比 ( 多  需要开始删除 ) 字符
            // 下面的for循环就是找到要删除的 ) 同时如果出现连续的 ) 只能删除第一个 否则会出现重复的结果
            for (int j = startRemovePos; j <= i; ++j) {
                //两种情况
                // 1. s.charAt(j) == par[1] &&  s.charAt(j - 1) != par[1]  检查重复 只移旭重复字符的第一个位置
                // 2. s.charAt(j) == par[1] && j == start_remove_pos 若移除的位置就是开始位置 不需要检查重复
                if (s.charAt(j) == par[1] && (j == startRemovePos || s.charAt(j - 1) != par[1])) {
                    // 递归调用start_count_pos还是i, start_remove_pos还是j, 是因为已经删除了一个字符 +1 -1 抵消
                    remove(s.substring(0, j) + s.substring(j + 1), ans, i, j, par);
                }
            }
            // 递归调用remove处理了后面的所有字符串 不需要再循环了
            return;
        }

        // 如果遇到 ( 比 ）多的情况 反转字符串和par
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') {// finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});  // 所有的字符串都会走一遍这个方法
        } else {  // finished right to left
            ans.add(reversed);   // 走到这里的时候 字符串已经反转再反转 所以就是原顺序了
        }
    }
}
