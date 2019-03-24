#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

/**
 * Given an array A of positive integers, A[i] represents the value of the i-th
 * sightseeing spot, and two sightseeing spots i and j have distance j - i
 *between them. The score of a pair (i < j) of sightseeing spots is (A[i] + A[j]
 *+ i - j) : the sum of the values of the sightseeing spots, minus the distance
 *between them. Return the maximum score of a pair of sightseeing spots. Example
 *1: Input: [8,1,5,2,6] Output: 11 Explanation: i = 0, j = 2, A[i] + A[j] + i
 *- j = 8 + 5 + 0 - 2 = 11
 **/

// O(n) time + O(n) space
int maxScoreSightseeingPair(vector<int>& A) {
  // 记录i位置后 A[j]-j的最大值
  vector<int> dp(A.size() - 1, 0);
  int cur_max = INT_MIN;
  for (int i = A.size() - 2; i >= 0; i--) {
    dp[i] = max(cur_max, A[i + 1] - i - 1);
    cur_max = max(cur_max, dp[i]);
  }

  int res = 0;
  for (int i = 0; i < A.size() - 1; i++) {
    res = max(res, A[i] + i + dp[i]);
  }
  return res;
}

// O(n) time + O(1) space
int maxScoreSightseeingPair1(vector<int>& A) {
  int cur_max = INT_MIN, res = INT_MIN;
  for (int i = A.size() - 2; i >= 0; i--) {
    cur_max = max(cur_max, A[i + 1] - i - 1);
    res = max(res, A[i] + i + cur_max);
  }
  return res;
}


int main() {
    vector<int> A{8, 1, 5, 2, 6};
    maxScoreSightseeingPair(A);
}
