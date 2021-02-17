package leetCode.twoPoint;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 */
public class Problem125 {

    public boolean isPalindrome(String s) {
        if (s.equals("")) return true;
        int start = 0, end = s.length() - 1;
        while (start < end) {
            while (start < s.length() && !Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            }
            while (end >= 0 && !Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            }
            if (start >= s.length() || end < 0 || start >= end) return true;
            if (!equalIgnoreCase(s.charAt(start), s.charAt(end))) return false;
            start++;
            end--;
        }
        return true;
    }

    private boolean equalIgnoreCase(char c1, char c2) {
        if (Character.isDigit(c1)) return c1 == c2;
        else return c1 == c2 || Math.abs(c1 - c2) == 'a' - 'A';
    }
}
