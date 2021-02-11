#include <unordered_set>
#include <vector>

/**
 * Given an unsorted array of integers nums, return the length of the longest
 * consecutive elements sequence.
 */

// hashset O(n) time + O(n) space
int longestConsecutive(std::vector<int>& nums) {
  if (nums.size() == 0) return 0;
  std::unordered_set<int> info;
  int res = 1;
  for (int i = 0; i < nums.size(); ++i) info.insert(nums[i]);
  for (auto k : info) {
    int l = 1, r = 1;
    while (info.count(k - l) > 0) {
      info.erase(k - l);
      l++;
    }
    while (info.count(k + r) > 0) {
      info.erase(k + r);
      r++;
    }
    res = std::max(res, r + l - 1);
  }
  return res;
}