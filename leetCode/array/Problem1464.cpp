#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

/**
 * Given the array of integers nums, you will choose two different indices i and
 * j of that array. Return the maximum value of (nums[i]-1)*(nums[j]-1).
 */
// one linear scan to keep the largest and the second largest element.
int maxProduct(vector<int>& nums) {
  int m1 = 0, m2 = 0;
  for (auto n : nums) {
    if (n > m1) {
      m2 = m1;
      m1 = n;
    } else {
      m2 = max(m2, n);
    }
  }
  return (m1 - 1) * (m2 - 1);
}