/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * Note: Your solution should be in logarithmic time complexity.
 */
#include <iostream>

int trailingZeroes(int n) {
    int res = 0;
    for (long long i = 5; i <= n && n / i > 0; i *= 5) {
        res += n / i;
    }
    return res;
}
