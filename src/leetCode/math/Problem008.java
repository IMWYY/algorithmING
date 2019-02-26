package leetCode.math;

/**
 * Implement atoi which converts a string to an integer.
 * <p>
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character
 * is found. Then, starting from this character, takes an optional initial plus or minus sign followed by
 * as many numerical digits as possible, and interprets them as a numerical value.
 * <p>
 * The string can contain additional characters after those that form the integral number,
 * which are ignored and have no effect on the behavior of this function.
 * <p>
 * If the first sequence of non-whitespace characters in str is not a valid integral number,
 * or if no such sequence exists because either str is empty or it contains only whitespace characters,
 * no conversion is performed.
 * <p>
 * If no valid conversion could be performed, a zero value is returned.
 * <p>
 * Note:
 * <p>
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer
 * range: [−231,  231 − 1]. If the numerical value is out of the range of representable values,
 * INT_MAX (231 − 1) or INT_MIN (−231) is returned.
 * <p>
 * create by stephen on 2018/9/22
 */
public class Problem008 {

    /**
     * 对于溢出的情况需要特殊处理 特别是Integer.MIN_VALUE
     */
    public int myAtoi(String str) {
        str = str.trim();
        if (str.length() == 0) return 0;
        boolean isPos = true;
        int start = 0, end = str.length();
        if (str.charAt(0) == '-') {
            isPos = false;
            start++;
        } else if (str.charAt(0) == '+') {
            start++;
        }

        for (int i = start; i < str.length(); ++i) {
            if (!Character.isDigit(str.charAt(i))) {
                end = i;
                break;
            }
        }

        int res = 0;
        for (int i = end - 1; i >= start; i--) {
            double diff = (str.charAt(i) - '0') * Math.pow(10, end - 1 - i);
            if (isPos) {
                if (Integer.MAX_VALUE - res < diff) {
                    return Integer.MAX_VALUE;
                }
                res += diff;
            } else {
                //  特别需要注意 0-Integer.MIN_VALUE的结果是溢出的 所以要加一个res<0的条件限制
                if (res < 0 && res - Integer.MIN_VALUE < diff) {
                    return Integer.MIN_VALUE;
                }
                res -= diff;
            }
        }
        return res;
    }
}
