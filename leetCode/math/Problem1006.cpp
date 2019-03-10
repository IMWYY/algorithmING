//
// Created by 王友运 on 2019-03-10.
//
/**
 * Normally, the factorial of a positive integer n is the product of all positive integers less than or equal to n.
 * For example, factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1.
 * We instead make a clumsy factorial: using the integers in decreasing order, we swap out
 * the multiply operations for a fixed rotation of operations: multiply (*), divide (/),
 * add (+) and subtract (-) in this order.
 *
 * For example, clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1.  However, these operations are
 * still applied using the usual order of operations of arithmetic: we do all multiplication and division
 * steps before any addition or subtraction steps, and multiplication and division steps are processed left to right.
 *
 * Additionally, the division that we use is floor division such that 10 * 9 / 8 equals 11.
 * This guarantees the result is an integer.
 * Implement the clumsy function as defined above: given an integer N, it returns the clumsy factorial of N.
 *
 * Example 1:
 * Input: 4
 * Output: 7
 * Explanation: 7 = 4 * 3 / 2 + 1
 */

int clumsy(int N) {
    if (N <= 2) return N;
    if (N == 3) return 6;
    if (N == 4) return 7;
    if (N == 5) return 7;
    int res = N * (N - 1) / (N - 2);
    if ((N - 3) % 4 == 0) {
        return res - 2;
    } else if ((N - 3) % 4 == 1) {
        return res;
    } else {
        return res + 1;
    }
}