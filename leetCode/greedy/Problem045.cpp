#include <climits>
#include <vector>

/**
 * Given an array of non-negative integers nums, you are initially positioned at
 * the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that
 * position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * You can assume that you can always reach the last index.
 */
int jump1(std::vector<int>& nums) {
  std::vector<int> steps(nums.size(), INT_MAX);
  steps[0] = 0;
  int farpos = 0;
  for (int i = 0; i < nums.size(); ++i) {
    bool canjump = farpos >= i;
    if (canjump && i + nums[i] > farpos) {
      for (int j = farpos + 1; j <= std::min((int)nums.size() - 1, i + nums[i]);
           ++j) {
        steps[j] = std::min(steps[i] + 1, steps[j]);
      }
      farpos = i + nums[i];
    }
    if (farpos >= nums.size() - 1) return steps.back();
  }
  return 0;
}

// current jump can reach [cur_begin, cur_end]
// update the range after each jump
int jump2(std::vector<int>& nums) {
  int jumps = 0, curEnd = 0, curFarthest = 0;
  for (int i = 0; i < nums.size() - 1; i++) {
    curFarthest = std::max(curFarthest, i + nums[i]);
    if (i == curEnd) {
      jumps++;
      curEnd = curFarthest;
    }
  }
  return jumps;
}