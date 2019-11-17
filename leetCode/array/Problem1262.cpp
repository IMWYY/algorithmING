
#include <iostream>
#include <numeric>
#include <queue>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

/**
 * Given an array nums of integers, we need to find the maximum possible sum of
 * elements of the array such that it is divisible by three.
 */

// get sum first, remove the min element(s) that result in the same reminder with sum
int maxSumDivThree(vector<int>& nums) {
  std::sort(nums.begin(), nums.end());
  int sum = 0;
  for (int i = 0; i < nums.size(); ++i) {
    sum += nums[i];
  }
  if (sum % 3 == 0) return sum;

  int reminder1 = 1, reminder2 = 2;
  if (sum % 3 == 2) {
    reminder1 = 2;
    reminder2 = 1;
  }

  int r1 = -1;
  int r2a = -1, r2b = -1;
  for (int i = 0; i < nums.size(); ++i) {
    if (r1 > 0 && r2a > 0 && r2b > 0) break;
    if (nums[i] % 3 == reminder1 && r1 < 0) r1 = nums[i];
    if (nums[i] % 3 == reminder2) {
      if (r2a < 0) {
        r2a = nums[i];
      } else {
        if (r2b < 0) r2b = nums[i];
      }
    }
  }
  if (r1 < 0) return sum - r2a - r2b;
  if (r2b < 0) return sum - r1;
  if (r1 < r2a + r2b) return sum - r1;
  return sum - r2a - r2b;
}