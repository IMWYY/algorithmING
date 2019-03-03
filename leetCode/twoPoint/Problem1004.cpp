//
// Created by 王友运 on 2019-03-03.
//

#include <vector>
#include <iostream>

using namespace std;

/**
 * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
 * Return the length of the longest (contiguous) subarray that contains only 1s.
 *
 * Example 1:
 * Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * Output: 6
 * Explanation:
 * [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
 */
int longestOnes(vector<int> &A, int K) {
    if (A.empty()) return 0;
    int start = 0, end = 0, count = 0, res = 0;
    while (end < A.size()) {
        while (end < A.size() && (count < K || (count == K && A[end] == 1))) {
            if (A[end++] == 0) count++;
        }
        res = max(res, end - start);
        if (end == A.size()) return res;
        if (A[start++] == 0) count--;
    }
    return res;
}