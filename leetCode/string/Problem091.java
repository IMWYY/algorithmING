package leetCode.string;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * create by stephen on 2018/9/28
 */
public class Problem091 {

    public static void main(String[] args) {
        System.out.println(new Problem091().numDecodings1("0001"));
    }


    /**
     * 动态规划（将递归转换为了动态规划）
     */
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[] memo = new int[n + 1];
        memo[n] = 1; // len
        memo[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;

        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') continue;
            memo[i] = (Integer.parseInt(s.substring(i, i + 2)) <= 26) ? memo[i + 1] + memo[i + 2] : memo[i + 1];
        }

        return memo[0];
    }

    /**
     * 递归 需要对0特殊处理 因为会有10和20
     * time limit exceed
     */
    public int numDecodings1(String s) {
        if (s.length() == 0) return 1;
        if (s.charAt(0) == '0') return 0;
        if (s.length() == 1) return 1;

        if (Integer.parseInt(s.substring(0, 2)) <= 26) {
            return numDecodings1(s.substring(1)) + numDecodings1(s.substring(2));
        } else {
            return numDecodings1(s.substring(1));
        }
    }
}
