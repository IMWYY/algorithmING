
#include <algorithm>
#include <bitset>
#include <climits>
#include <cmath>
#include <deque>
#include <iostream>
#include <unordered_map>
#include <vector>

/**
 * You are given a 0-indexed integer array nums and an integer k.
 *
 * You are initially standing at index 0. In one move, you can jump at most k
 * steps forward without going outside the boundaries of the array. That is, you
 * can jump from index i to any index in the range [i + 1, min(n - 1, i + k)]
 * inclusive.
 *
 * You want to reach the last index of the array (index n - 1). Your score is
 * the sum of all nums[j] for each index j you visited in the array.
 *
 * Return the maximum score you can get.
 *
 * 1 <= nums.length, k <= 105
 * -104 <= nums[i] <= 104
 */
// dp solution got TLE, beacuse k can be pretty large.
// Actually, we don't have to iterate each index in [i,i+k].
// From end to begin (rigth to left), we keep the a sliding window to keep
// the max score (s) between [i, i+k], then the score at i can be simply
// calculted by nums[i] + s.
int maxResult(std::vector<int>& nums, int k) {
  int n = nums.size();
  std::vector<int> scores(n, INT_MIN);
  std::deque<int> q;
  for (int i = n - 1; i >= 0; i--) {
    if (!q.empty() && q.front() > i + k) {
      q.pop_front();  // keep the window small
    }

    int cur = nums[i] + (q.empty() ? 0 : scores[q.front()]);

    while (!q.empty() && cur > scores[q.back()]) {
      q.pop_back();  // keep the deque sorted
    }
    scores[i] = cur;
    q.push_back(i);
  }
  return scores[0];
}
