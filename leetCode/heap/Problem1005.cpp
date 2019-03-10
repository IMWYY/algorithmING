//
// Created by 王友运 on 2019-03-10.
//

#include <iostream>
#include <vector>
#include <queue>

using namespace std;

/**
 * Given an array A of integers, we must modify the array in the following way: we choose an i and replace A[i]
 * with -A[i], and we repeat this process K times in total.  (We may choose the same index i multiple times.)
 * Return the largest possible sum of the array after modifying it in this way.
 *
 * Example 1:
 * Input: A = [4,2,3], K = 1
 * Output: 5
 * Explanation: Choose indices (1,) and A becomes [4,-2,3].
 */

int largestSumAfterKNegations(vector<int> &A, int K) {
    if (A.size() == 0) return 0;
    int sum = 0;
    priority_queue<int, vector<int>, greater<> > q;
    for (int i : A) {
        q.push(i);
        sum += i;
    }
    while (K > 0) {
        int a = q.top();
        if (a < 0) {
            q.pop();
            q.push(-a);
            sum -= 2 * a;
            K--;
        } else {
            if (a > 0 && (K & 1) == 1) {
                sum -= 2 * a;
            }
            break;
        }
    }
    return sum;
}

int main() {
    vector<int> a{-2, 9, 9, 8, 4};
    cout << largestSumAfterKNegations(a, 5);
}