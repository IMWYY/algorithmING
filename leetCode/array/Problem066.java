package leetCode.array;

/**
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 */
public class Problem066 {

    public int[] plusOne(int[] digits) {
        for (int i=digits.length-1; i>=0; --i) {
            if (digits[i] < 9) {
                digits[i] ++;
                return digits;
            }
            digits[i] = 0; // it must be 0 after a carry
        }
        int[] result = new int[digits.length+1];
        result[0] = 1;
        return result;
    }
}
