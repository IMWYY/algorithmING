package leetCode.twoPoint;

/**
 * Implement strStr().
 * <p>
 * Return the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 * <p>
 * Example 1:
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * <p>
 * Example 2:
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * <p>
 * create by stephen on 2018/9/22
 */
public class Problem028 {


    // 更简洁
    public int strStr(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }

    // my solution
    public int strStr1(String haystack, String needle) {
        if (needle.equals("")) return 0;

        for (int i = 0; i < haystack.length(); ++i) {
            if (i + needle.length() > haystack.length()) return -1;

            if (haystack.charAt(i) == needle.charAt(0)) {
                int p1 = i, p2 = 0;
                while (haystack.charAt(p1) == needle.charAt(p2)) {
                    p1 ++;
                    p2 ++;
                    if (p2 == needle.length()) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }
}
