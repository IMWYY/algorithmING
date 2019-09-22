#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

using namespace std;

/**
 * Given an integer array arr and an integer k, modify the array by repeating it
 * k times.
 *
 * For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2,
 * 1, 2, 1, 2].
 *
 * Return the maximum sub-array sum in the modified array. Note that the length
 * of the sub-array can be 0 and its sum in that case is 0.
 *
 * As the answer can be very large, return the answer modulo 10^9 + 7.
 */

// if the sum of arr is less than 0, 
// than the result is the maximum sum of sub array
// else the result if (k-2) * sumOfArr + maximum prefix + maximum suffix
int kConcatenationMaxSum(vector<int>& arr, int k) {
  long long res = 0, cur_sum = 0;
  if (k == 1 || k == 2) {
    for (int i = 0; i < arr.size() * k; ++i) {
      cur_sum += arr[i % arr.size()];
      res = std::max(res, cur_sum);
      if (cur_sum < 0) cur_sum = 0;
    }
    return (res) % (long long)(std::pow(10, 9) + 7);
  }

  long long sum_one_round = std::accumulate(arr.begin(), arr.end(), 0);
  if (sum_one_round > 0) {
    long long max_prev = 0, max_suffix = 0, prev = 0, suffix = 0;
    for (int i = 0; i < arr.size(); ++i) {
      prev += arr[i];
      suffix += arr[arr.size() - 1 - i];
      max_prev = std::max(max_prev, prev);
      max_suffix = std::max(max_suffix, suffix);
    }
    res = max_prev + max_suffix + (k - 2) * sum_one_round;
  } else {
    for (int i = 0; i < arr.size() * 2; ++i) {
      cur_sum += arr[i % arr.size()];
      res = std::max(res, cur_sum);
      if (cur_sum < 0) cur_sum = 0;
    }
  }

  return (res) % (long long)(std::pow(10, 9) + 7);
}