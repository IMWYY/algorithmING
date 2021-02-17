package leetCode.string;

/**
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.
 * see wiki https://en.wikipedia.org/wiki/Look-and-say_sequence
 */
public class Problem038 {
    public String countAndSay(int n) {
        if (n == 1) return "1";
        if (n == 2) return "11";
        String s = "11";
        for (int i = 2; i < n; ++i) {
            StringBuilder sb = new StringBuilder();
            int j = 0, len;
            while (j < s.length()) {
                char c = s.charAt(j);
                len = 0;
                while (j < s.length() && c == s.charAt(j)) {
                    len++;
                    j++;
                }
                sb.append(len).append(c);
            }
            s = sb.toString();
        }
        return s;
    }
}
