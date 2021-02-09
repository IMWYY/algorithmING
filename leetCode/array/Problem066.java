package leetCode.array;

/**
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 * <p>
 * The digits are stored such that the most significant digit is at the head of the list,
 * and each element in the array contain a single digit.
 * <p>
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Example 2:
 * <p>
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 * create by stephen on 2018/7/2
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
