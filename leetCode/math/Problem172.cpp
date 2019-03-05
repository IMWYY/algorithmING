//
// Created by 王友运 on 2019-03-05.
//
/**
 * Given an integer n, return the number of trailing zeroes in n!.
 *
 * Example 1:
 * Input: 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 *
 * Example 2:
 * Input: 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 * Note: Your solution should be in logarithmic time complexity.
 */
#include <iostream>

using namespace std;

int trailingZeroes(int n) {
    int res = 0;
    for (long long i = 5; i <= n && n / i > 0; i *= 5) {
        res += n / i;
    }
    return res;
}


int main() {
    cout << trailingZeroes(25) << endl;
}
